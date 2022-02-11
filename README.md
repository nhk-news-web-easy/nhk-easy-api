# nhk-easy-api [![CircleCI](https://circleci.com/gh/nhk-news-web-easy/nhk-easy-api/tree/master.svg?style=svg)](https://circleci.com/gh/nhk-news-web-easy/nhk-easy-api/tree/master) [![Build status](https://ci.appveyor.com/api/projects/status/767xl0axpid4xi4g/branch/master?svg=true)](https://ci.appveyor.com/project/Frederick-S/nhk-easy-api-l9nej/branch/master) [![codecov](https://codecov.io/gh/Frederick-S/nhk-easy-api/branch/master/graph/badge.svg)](https://codecov.io/gh/Frederick-S/nhk-easy-api) [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

API of [nhk-easy-mobile](https://github.com/nhk-news-web-easy/nhk-easy-mobile), this repo depends on [nhk-easy-task](https://github.com/nhk-news-web-easy/nhk-easy-task) to fetch and parse news from [NEWS WEB EASY](https://www3.nhk.or.jp/news/easy/).

## Getting started
```sh
docker run -e MYSQL_HOST=ip-address-of-mysql \
  -e MYSQL_USER=your-mysql-user \
  -e MYSQL_PASSWORD=your-mysql-user-password \
  -p 8080:8080 -d xiaodanmao/nhk-easy-api
```

## License
[MIT](LICENSE)
