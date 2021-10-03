set -exo
git checkout gh-pages
git merge 1.x
sbt fullLinkJS
rm -rf js
cp -R ./target/scala-2.13/lnurl-codec-opt ./js
git add js
git commit -m "update"
git push upstream head
