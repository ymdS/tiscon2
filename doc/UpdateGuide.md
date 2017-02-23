# 更新手引き

fork元の本家リポジトリ( https://github.com/tiscon/tiscon2 ) に更新があった時、それを自分のリポジトリに取り込む手順です。

## 前提条件

[Gitルーキートラの巻](GitForRookies.md)を一読してある、もしくは内容が分かること。

## 本家リポジトリを参照できるようにする( `git remote add` )

https://github.com/tiscon/tiscon2.git を tiscon という名前でリモートリポジトリに追加します。

IntelliJで `VCS` > `Git` > `Remotes` をクリック。

![IntelliJでGit Remotesを開く](image/update_guide_add_remote_1.png)

nameに `tiscon` 、 URLに `https://github.com/tiscon/tiscon2.git` と入力してOKをクリック。

![IntelliJでRemote追加](image/update_guide_add_remote_2.png)

## 本家リポジトリの変更を取り込む

tisconのmasterブランチをローカルのmasterブランチにダウンロードします。

[リモートリポジトリの内容をダウンロード(Pull)する | Gitルーキートラの巻 :tiger:](GitForRookies.md#リモートリポジトリの内容をダウンロードpullする)と同様の手順でダウンロードしてください。

## 自分のリポジトリに変更をアップロードする

[ローカルリポジトリの内容をアップロードpushする | Gitルーキートラの巻 :tiger:](GitForRookies.md#ローカルリポジトリの内容をアップロードpushする)と同様の手順で、originリポジトリに変更をPushしてください。

## チームメンバーのリポジトリの更新を自分のリポジトリに取り込みたい
1. [本家リポジトリを参照できるようにする( `git remote add` )](#本家リポジトリを参照できるようにする-git-remote-add-) と同じ手順で、更新を取り込みたいチームメンバーのリポジトリを参照できるようにします。その時、登録するURLは `https://github.com/更新を取り込みたいチームメンバーのGitHubアカウント名/tiscon2.git` で、その時の登録名はそのメンバーのGitHubアカウント名にしましょう。
1. [リモートリポジトリの内容をダウンロード(Pull)する | Gitルーキートラの巻 :tiger:](GitForRookies.md#リモートリポジトリの内容をダウンロードpullする)と同様の手順でダウンロードしてください。その時、ダウンロード元のリモートリポジトリは、更新を取り込みたいチームメンバーのものを選択してください。
1. [ローカルリポジトリの内容をアップロードpushする | Gitルーキートラの巻 :tiger:](GitForRookies.md#ローカルリポジトリの内容をアップロードpushする)と同様の手順で、originリポジトリに変更をPushしてください。
