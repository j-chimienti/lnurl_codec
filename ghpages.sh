git checkout gh-pages
sbt fullLinkJS
rm -rf js
cp -R ./target/scala-2.13/scala-js-tutorial-opt ./js
git add js
git commit -m "update"
gpuh
