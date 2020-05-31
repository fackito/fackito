#!/bin/bash
set -ev
if [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_COMMIT_MESSAGE" != "AUTO Version Increase" ]; then
  echo 'increasing version'
  mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.incrementalVersion} versions:commit
  git add .
  git commit -m "AUTO Version Increase"
  git push origin master
elif [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_COMMIT_MESSAGE" == "AUTO Version Increase" ]; then
  echo 'deploying'
  mvn deploy --settings .travis/settings.xml
else
  echo 'skipping'
fi
