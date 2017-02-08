# Gitルーキートラの巻 :tiger:

## Gitとは

ファイルのバージョン管理ツールです。詳しくは以下サイトが非常にわかりやすいです。

* [サルでもわかるGit入門](http://www.backlog.jp/git-guide/)
* [Gitチュートリアル | Atlassian](https://www.atlassian.com/ja/git)

特に[サルでもわかるGit入門](http://www.backlog.jp/git-guide/)は非常に秀逸です。
[Gitの基本](http://www.backlog.jp/git-guide/intro/intro1_1.html)では、Git自体の概要と基本的なキーワードを学べます。
[リポジトリの共有](http://www.backlog.jp/git-guide/intro/intro3_1.html)では、ソースコードの変更をアップロード / ダウンロードする方法が学べます。

ぜひ一読してみてください。
「リポジトリ」「アド」「コミット」「プッシュ」「プル」といわれてなんとなくイメージが湧けばOKです！

## IntelliJでのGitの使い方

以下の記事が参考になります。

* [意外と知らないIntelliJ IDEAのGit管理機能いろいろ（´-`） - Qiita](http://qiita.com/yoppe/items/fd03607d4d4f191d32dd)
* [IntelliJ IDEAでGitとGitHubを使用する方法 - TASK NOTES](http://www.task-notes.com/entry/20160511/1462935600)

特によく使う機能については、以下にまとめておきます。

### 変更内容をコミット(Commit)する

現在の作業状態を将来見直せる形でセーブしたい時に使います。つまり、コーヒーを買いに行く時には使いませんが仕事を終えて帰宅する前やタスクが終わった時には使います。

#### 新規に追加したファイルは Changelist に追加する

IntelliJでファイルを新規作成すると、作成時に![ファイルをGitに追加しますか？](image/git_for_rookie_add_file_1.png)と尋ねられるので、「Yes」と答えておいてください。これでファイルが [add](https://www.atlassian.com/ja/git/tutorial/git-basics#!add) されます。

もしIntelliJ以外からファイルを追加した時(画像ファイルをディレクトリ移動させてきた時など)は、 `Version Control` タブで add したいファイルをクリックしてから `Move to Another Changelist` ボタンをクリックして、 ![Move to Another Changelist](image/git_for_rookie_add_file_2.png) ファイルを `Default` に追加しましょう。 ![add file to Default Changelist](image/git_for_rookie_add_file_3.png)

ちなみに Changelist は IntelliJ 特有の仕組みです。

#### コミットする

コミットしたくなったら、 `Version Control` タブの `Default` 内のリストを見てみてください。変更したいものがすべてこの内部に入っていれば準備OKです。

そして `Version Control` タブの `Commit Changes` ボタンをクリックして、 ![Commit Changes](image/git_for_rookie_commit_1.png) コミット画面を開きます。 ![コミット画面を開く](image/git_for_rookie_commit_2.png)

コミットの内容が分かるようなメッセージを **必ず** 書いて、 `Commit` ボタンをクリックすればコミット完了です。

### リモートリポジトリの内容をダウンロード(Pull)する



### ローカルリポジトリの内容をアップロード(Push)する
