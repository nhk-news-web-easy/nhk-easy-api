# nhk-easy-api 

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
