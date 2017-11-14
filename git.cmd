git add .
git status
git config --global user.email "j.b.olszewski@gmail.com" 
git config --global user.name "jakub-olszewski" 
git commit -m $1
git push -u origin master
git rm -r --cached .
