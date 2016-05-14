$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"

pwd 		 //版本仓库的位置
ls		 //当前目录的所有文件 ls -ah可看到隐藏文件
mkdir		 //创建新目录

$ git init       //把这个目录变成Git可以管理的仓库

//在仓库内新建文件readme.txt
$ git add readme.txt	//将文件添加到仓库中
$ git add file1.txt
$ git add file2.txt file3.txt
$ git commit -m "add 3 files."	//把文件提交到仓库 -m后面输入的是本次提交的说明

/**
如果不小心输成了$ git commit 则 :wq 回车
**/

$ git status		//命令可以让我们时刻掌握仓库当前的状态
$ git diff readme.txt  	//能看看具体修改了什么内容

$ git log			//命令可以告诉我们历史记录
$ git log --pretty=oneline	//一条历史记录显示在一行

$ git reset --hard HEAD^	//回退到上一个版本上一个版本就是HEAD^，上上一个版本就是					HEAD^^，版本过多HEAD~100

$ git reset --hard 3628164	//根据commit id指定回到未来的某个版本
$ git reflog			//用来记录你的每一次命令 含有commit id

工作区 版本库 暂存区（Stage）
工作区：git init的那个目录。
版本库：目录下.git文件夹。
暂存区：执行add命令后 未commit时文件的位置。 而commit命令就是把暂存区的文件提交

$ git checkout -- readme.txt		//必须是commit后修改，或者add后修改	//修改包括删除	
命令git checkout -- readme.txt意思就是，把readme.txt文件在工作区的修改全部撤销，这里有两种情况：
一种是readme.txt自修改后还没有被放到暂存区，现在，撤销修改就回到和版本库一模一样的状态；
一种是readme.txt已经添加到暂存区后，又作了修改，现在，撤销修改就回到添加到暂存区后的状态。
总之，就是让这个文件回到最近一次git commit或git add时的状态。

$ rm test.txt 		//从文件夹删除文件
第一种情况：确实要从版本库中删除该文件		
$ git rm test.txt	//从版本库中删除该文件
$ git commit -m "remove test.txt"

第二种情况：恢复该文件	
$ git checkout -- test.txt

github的使用
1.创建SSH Key	$ ssh-keygen -t rsa -C "youremail@example.com"
2.在github中添加SSH key 
3.在github中Create a new repo
4.把一个已有的本地仓库与之关联 $ git remote add origin git@github.com:michaelliao/learngit.git
	注意：此时github中repo必须为空，如果不为空则会报错failed to push some refs to git
	此时可以	$ git pull --rebase origin master合并
5.把本地库的所有内容推送到远程库上 $ git push -u origin master

由于远程库是空的，我们第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送的远程新的master分支，还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令
$ git push origin master

$ git clone git@github.com:michaelliao/gitskills.git	上次我们讲了先有本地库，后有远程库的时候，如何关联远程库。现在，假设我们从零开发，那么最好的方式是先创建远程库，然后，从远程库克隆。

$ git checkout -b dev		//创建dev分支，然后切换到dev分支

git checkout命令加上-b参数表示创建并切换，相当于以下两条命令
$ git branch dev
$ git checkout dev

$ git branch		//查看当前分支
$ git merge dev		//合并到master分支上
$ git branch -d dev	//删除dev分支
$ git branch -D dev	//分支没合并时强制删除分支

解决冲突:有冲突的时候可以用status看到， 可以手动解决。
$ git log --graph --pretty=oneline --abbrev-commit	以图形的方式展现

通常，合并分支时，如果可能，Git会用Fast forward模式，但这种模式下，删除分支后，会丢掉分支信息。
如果要强制禁用Fast forward模式，Git就会在merge时生成一个新的commit，这样，从分支历史上就可以看出分支信息。
$ git merge --no-ff -m "merge with no-ff" dev


bug分支：软件开发中，bug就像家常便饭一样。有了bug就需要修复，在Git中，由于分支是如此的强大，所以，每个bug都可以	通过一个新的临时分支来修复，修复后，合并分支，然后将临时分支删除。
	当你接到一个修复一个代号101的bug的任务时，很自然地，你想创建一个分支issue-101来修复它，但是，等等，当前	正在dev上进行的工作还没有提交。
Git还提供了一个stash功能，可以把当前工作现场“储藏”起来，等以后恢复现场后继续工作：
$ git stash		加入的顺序是从0开始往后移位，类似于栈
$ git stash list

$ git stash apply	恢复后，stash内容并不删除
$ git stash drop	删除
$ git stash pop		恢复的同时把stash内容也删了

多人协作：
$ git remote	查看远程库的信息，远程仓库的默认名称是origin
$ git remote -v 显示更详细的信息
$ git push origin master	推送分支
$ git push origin dev

$ git clone git@github.com:michaelliao/learngit.git
$ git checkout -b dev origin/dev	//创建远程origin的dev分支到本地
$ git commit -m "add /usr/bin/env"
$ git push origin dev		//发生冲突时

推送失败，因为你的小伙伴的最新提交和你试图推送的提交有冲突，解决办法也很简单，Git已经提示我们，先用git pull把最新的提交从origin/dev抓下来，然后，在本地合并，解决冲突，再推送

git pull也失败了，原因是没有指定本地dev分支与远程origin/dev分支的链接，根据提示，设置dev和origin/dev的链接
$ git branch --set-upstream dev origin/dev
$ git pull