set -exo
sbt fullLinkJS
cp -R ./target/scala-2.13/lnurl-codec-opt/main.js ./docs
