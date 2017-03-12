package net.unit8.sigcolle.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import enkan.component.doma2.DomaProvider;
import enkan.data.Flash;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.auth.LoginUserPrincipal;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.dao.SignatureDao;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.CampaignCreateForm;
import net.unit8.sigcolle.form.CampaignForm;
import net.unit8.sigcolle.form.SignatureForm;
import net.unit8.sigcolle.model.Campaign;
import net.unit8.sigcolle.model.Signature;
import net.unit8.sigcolle.model.User;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;
import static enkan.util.ThreadingUtils.some;

/**
 * @author kawasima
 */
public class CampaignController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    /**
     * キャンペーン詳細画面表示.
     *
     *
     * @param form  URLパラメータ
     * @param flash flash scope session
     * @return HttpResponse
     */

    public HttpResponse index(CampaignForm form, Flash flash) {
        if (form.hasErrors()) {
            HttpResponse response = HttpResponse.of("Invalid");
            response.setStatus(400);
            return response;
        }

        return showCampaign(form.getCampaignIdLong(),
                new SignatureForm(),
                (String) some(flash, Flash::getValue).orElse(null));
    }

    /**
     * 署名の追加処理.
     *
     * @param form 画面入力された署名情報.
     * @return HttpResponse
     */
    @Transactional
    public HttpResponse sign(SignatureForm form) {
        if (form.hasErrors()) {
            return showCampaign(form.getCampaignIdLong(), form, null);
        }
        Signature signature = new Signature();
        signature.setCampaignId(form.getCampaignIdLong());
        signature.setName(form.getName());
        signature.setSignatureComment(form.getSignatureComment());

        SignatureDao signatureDao = domaProvider.getDao(SignatureDao.class);
        signatureDao.insert(signature);

        HttpResponse response = redirect("/campaign/" + form.getCampaignId(), SEE_OTHER);
        response.setFlash(new Flash<>("ご賛同ありがとうございました！"));
        //response.setFlash(new Flash<>("ご協力ありがとうございました。"));
        return response;
    }

    /**
     * 新規キャンペーン作成画面表示.
     *
     * @return HttpResponse
     */
    public HttpResponse newCampaign() {
        return templateEngine.render("campaign/new", "form", new CampaignCreateForm());
    }

    /**
     * 新規キャンペーンを作成します.
     * ---------------------------------------
     * FIXME このメソッドは作成途中です.
     *
     * @param form    入力フォーム
     * @param session ログインしているユーザsession
     */
    @Transactional
    public HttpResponse create(CampaignCreateForm form,
                               Session session) {
        if (form.hasErrors()) {
            return templateEngine.render("campaign/new", "form", form);
        }
        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");

        PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);

        // TODO タイトル, 目標人数を登録する
        Campaign model = new Campaign();
        model.setStatement(processor.markdownToHtml(form.getStatement()));//Statementを入力している
        model.setCreateUserId(principal.getUserId());//作成userをgetしてくる
        //ここから改変部分

        model.setTitle(form.getTitle());//これはOK!タイトル
        //campaign.setStatement(form.getStatement());
        model.setGoal(Long.parseLong(form.getGoal()));//これはOK!!ゴールの数字

        //create_user_id
        //ここまで改変部分

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        // TODO Databaseに登録する

        //ここから改変部分
        campaignDao.insert(model);
        //ここまで改変部分

        HttpResponse response = redirect("/campaign/" + model.getCampaignId(), SEE_OTHER);
        response.setFlash(new Flash<>(""/* TODO: キャンペーンが新規作成できた旨のメッセージを生成する */));

        return response;
    }

    /**
     * ログインユーザの作成したキャンペーン一覧を表示します.
     * ---------------------------------------
     * FIXME このメソッドは作成途中です.
     *
     * @param session ログインしているユーザsession
     */

    public HttpResponse listCampaigns(Session session) {
        // ここから改変
        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");
        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        List<Campaign> campaign = campaignDao.selectByUserId(new Long(principal.getUserId()));
        //return templateEngine.render("index", "campaigns", campaignDao.selectByUserId(Long.parseLong("1")));
        return templateEngine.render("index",
                "campaigns", campaign
                );
        //ここまで変更
        //throw new UnsupportedOperationException("実装してください !!");
    }

    private HttpResponse showCampaign(Long campaignId,
                                      SignatureForm form,
                                      String message) {
        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(campaignId);
        UserDao userDao = domaProvider.getDao(UserDao.class);
        User user = userDao.selectByUserId(campaign.getCreateUserId());

        SignatureDao signatureDao = domaProvider.getDao(SignatureDao.class);
        int signatureCount = signatureDao.countByCampaignId(campaignId);
/*
<<<<<<< HEAD
=======
        //ここから変更
        List<Signature> signature = signatureDao.selectAllByCampaignId(campaignId);
>>>>>>> yamada-1
*/
        return templateEngine.render("campaign/index",
                "campaign", campaign,
                "user", user,
                "signatureCount", signatureCount,
                "signature", form,
                "message", message//,
               // "signature",signature//ここのみ変更
        );
    }
}
