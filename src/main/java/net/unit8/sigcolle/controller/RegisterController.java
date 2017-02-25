package net.unit8.sigcolle.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.auth.LoginUserPrincipal;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.RegisterForm;
import net.unit8.sigcolle.model.User;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * @author takahashi
 */
public class RegisterController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    private static final String EMAIL_ALREADY_EXISTS = "このメールアドレスは既に登録されています。";

    /**
     * ユーザー登録画面表示.
     *
     * @return HttpResponse
     */
    public HttpResponse index() {
        return templateEngine.render("user/register", "user", new RegisterForm());
    }

    /**
     * ユーザー登録処理.
     *
     * @param form 画面入力されたユーザー情報
     * @return HttpResponse
     */
    @Transactional
    public HttpResponse register(RegisterForm form) {

        if (form.hasErrors()) {
            return templateEngine.render("user/register", "user", form);
        }

        UserDao userDao = domaProvider.getDao(UserDao.class);

        // メールアドレス重複チェック
        if (userDao.countByEmail(form.getEmail()) != 0) {
            form.setErrors(Multimap.of("email", EMAIL_ALREADY_EXISTS));
            return templateEngine.render("user/register",
                                         "user", form
            );
        }

        User user = new User();
        user.setLastName(form.getLastName());
        user.setFirstName(form.getFirstName());
        user.setEmail(form.getEmail());
        user.setPass(form.getPass());

        userDao.insert(user);

        Session session = new Session();
        User loginUser = userDao.selectByEmail(form.getEmail());
        session.put(
                "principal",
                new LoginUserPrincipal(loginUser.getUserId(), loginUser.getLastName() + " " + loginUser.getFirstName())
        );

        HttpResponse response = redirect("/", SEE_OTHER);
        response.setSession(session);
        return response;
    }
}
