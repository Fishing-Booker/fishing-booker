# Fishing Booker

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=shield)](https://app.circleci.com/pipelines/github/Fishing-Booker/fishing-booker?filter=all)


* Potrebna okruženja za rad:
	- back-end: Intellij IDEA Community Edition (potrebno prethodno instalirati jdk 11)
	- front-end: Visual Studio Code
	- baza: My SQL 

* Preuzeti projekat sa grane: feat-januarskiRok

- Putem File->Open->putanja do mesta gde ste download-ovali projekat i do foldera fishingbooker(unutar fishing-booker), unutar Intellij okruženja otvoriti back-end deo projekta

- Po završetku instalacije My SQL baze, potrebno je napraviti bazu podataka sa kredencijalima:
	- name: fishingdb
	- port: 3306
	- username: isa
	- password: isa
	
	- u suprotnom, u okruženju Intellij u fajlu application.properties (u prve 3 linije) podesiti odgovarajuče kredencijale
		- application.properties se nalizi na sledećoj putanji: fishingbooker->src->main->resources->application.properties

* Pokretanje back-end dela:
	- na putanji: fishingbooker->src->main->java->com.example.fishingbooker nalazi se klasa FishingbookerApplication, desni klik na nju i zatim Run
	- projekat će se pokrenuti na portu localhost:8080
		- u slučaju da je port zauzet, potrebno je osloboditi ga

* Pokretanje front-end dela:
	- otvoriti projekat (fishing-booker-front) u Visual Studio Code uz pomoć File->Open Folder
	- u meniju kliknuti na opciju Terminal->New terminal
	- u terminal ukucati: npm install
	- po završetku instaliranja svih potrebnih paketa, u terminal ukucati: npm start i projekat će se pokrenuti na portu localhost:3000

* Popunjavanje baze podacima:
	- tabele će se samostalno generisati pokretanjem back-end dela projekta
	- podaci se unose tako što se podaci iz skipti (nalaze se u folderu "podaci za bazu") prekopiraju na belu površinu sa desne strane
	- !!!pratiti redosled popunjavanja tabela iz fajla "uputstvo za bazu" iz foldera "podaci za bazu"!!!
	- OBEVEZNO prethodno dvokliknuti na bazu u koju želite da unesete podatke (fishingdb) 
	- u gornjem levom uglu prostora za query-je nalazi se ikonica žute munje, i pritiskom na nju podaci će se upisati u bazu

* Za logovanje (kao korisnik odgovarajuće kategorije) koristiti username-ove i password-e iz fajla "korisnici i lozinke" iz foldera "podaci za bazu"

Za januarski rok preuzeti projekat sa grane: feat-januarkiRok

