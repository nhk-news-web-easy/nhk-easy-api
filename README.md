# nhk-easy-api [![CircleCI](https://circleci.com/gh/Frederick-S/nhk-easy-api.svg?style=shield)](https://circleci.com/gh/Frederick-S/nhk-easy-api) [![Build status](https://ci.appveyor.com/api/projects/status/wy3c4pjhnu58y0t2/branch/master?svg=true)](https://ci.appveyor.com/project/Frederick-S/nhk-easy-api/branch/master) [![codecov](https://codecov.io/gh/Frederick-S/nhk-easy-api/branch/master/graph/badge.svg)](https://codecov.io/gh/Frederick-S/nhk-easy-api) [![Maintainability](https://api.codeclimate.com/v1/badges/833bfaca0f7168f4ab30/maintainability)](https://codeclimate.com/github/Frederick-S/nhk-easy-api/maintainability) [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

API of [nhk-easy-mobile](https://github.com/Frederick-S/nhk-easy-mobile).

## Getting started
### Docker
```sh
docker run -e MYSQL_ROOT_PASSWORD=your-mysql-root-password -e MYSQL_DATABASE=nhk -e MYSQL_USER=your-mysql-user -e MYSQL_PASSWORD=your-mysql-user-password -p 3306:3306 -d mysql:8

docker run -e MYSQL_HOST=ip-address-of-mysql (inspected from docker inspect mysql-container) -e MYSQL_USER=your-mysql-user -e MYSQL_PASSWORD=your-mysql-user-password -p 8080:8080 xiaodanmao/nhk-easy-daily
```

### Docker compose
Run `cp .env.template .env` and modify user & password, then run `docker-compose up -d`.

## License
[MIT](LICENSE)
