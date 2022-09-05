# HITs FAQ Bot
Higher IT School FAQ Telegram-Bot with admin panel

## Technology stack
Java 11, Maven, Spring Boot, Spring MVC, Spring Data, Spring Security, Hibernate, PostgreSQL, Telegram Bot API.

## Demo
...

## How to run manually
1) Install software
* Update and upgrade: `sudo apt-get update && sudo apt-get upgrade`
* Install Git: `apt-get install git`
* Install Maven: `apt-get install maven`
* Install jdk 11: ` wget -O- https://apt.corretto.aws/corretto.key | sudo apt-key add - &&
  sudo add-apt-repository 'deb https://apt.corretto.aws stable main'`,
then `sudo apt-get update; sudo apt-get install -y java-11-amazon-corretto-jdk`
* Install PostgreSQL: `apt-get install postgresql postgresql-contrib`
* Clone repository: `git clone https://github.com/ivan909020/shop-telegram-bot.git`
* Package admin panel `mvn -f shop-telegram-bot/admin-panel/ package`
* Package telegram bot `mvn -f shop-telegram-bot/telegram-bot/ package`

2) Set environment variables
* DATABASE_URL (jdbc:postgresql://localhost:5432/hits_faq_bot)
* DATABASE_USERNAME (postgres)
* DATABASE_PASSWORD (postgres)

3) ...

## How to run with Docker
...

## Screenshots
### Telegram bot
...
### Admin panel
...

