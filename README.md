# nhk-easy-api [![Java CI with Maven](https://github.com/nhk-news-web-easy/nhk-easy-api/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/nhk-news-web-easy/nhk-easy-api/actions/workflows/build.yml) [![codecov](https://codecov.io/gh/nhk-news-web-easy/nhk-easy-api/branch/master/graph/badge.svg?token=U1U40B2CA3)](https://codecov.io/gh/nhk-news-web-easy/nhk-easy-api)

API of [nhk-easy-mobile](https://github.com/nhk-news-web-easy/nhk-easy-mobile), this repo depends on [nhk-easy-task](https://github.com/nhk-news-web-easy/nhk-easy-task) to fetch and parse news from [NEWS WEB EASY](https://www3.nhk.or.jp/news/easy/).

## Getting started
```sh
docker run -e MYSQL_HOST=ip-address-of-mysql \
  -e MYSQL_USER=your-mysql-user \
  -e MYSQL_PASSWORD=your-mysql-user-password \
  -p 8080:8080 \
  -d xiaodanmao/nhk-easy-api
```

## License
[MIT](LICENSE)
