INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_DEFADMIN');
INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_ADMIN');
INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_CLIENT');
INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_INSTRUCTOR');
INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_SHIPOWNER');
INSERT INTO roles VALUES (nextval('role_seq_gen'), 'ROLE_LODGEOWNER');

INSERT INTO users VALUES (nextval('user_seq_gen'),'Gogoljeva 5','Novi Sad','Serbia','isausermailsmn@gmail.com', true, false, true,'Admin','$2a$10$tRvPQwkBv/IAzpZ9uEJJUep8K.xom1cGdoCNVqYaGcWJgvnkcFiWC','069778189','Admin','admin','vsCcvzbEcAdCgcf0LYJ8vvKrT6NgCSZhEEMzKsrNumwUYm7u7i3yh3ie2QWxtj00');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Kolo srpskih sestara 20','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Marija','$2a$10$K2jL4QFmYTCc8Mt2hu99ZOer6ztrqnzYMFe81IEQhTfdyCh//2pea','0637319010','Petrovic','marija','MFj5RW4fx25F4R8oCRMoL1Hvp1FtEs0eos5sMsv0ZUTvbNvDYdFzjboUcWQsulCl');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Hadzi Ruvimova 41','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Stefan','$2a$10$lpdtEAN1GXX9mMTOBTHFhuKCob3l6BjH8fvHSCZEgLHCLs5g8Oyvi','064987564','Petrovic','stefan','iI9U58kRHZfAdyRBIKQWq0o1yOTMP9vlTbU33xzobHuaPANPLL4zQewujd63lpZ6');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Kosovska 87','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Sara','$2a$10$gFo7foDTioQY9tksA8sCuO2MuLTtUix7AgOgz92J.MfG6kDaTSSgm','061252604','Poparic','sara','P5o3Vm3k6aPCnF7eKEcbdwo2Rp2yImOdJnyW49DWeFOKE6tLn1HBT0I06v6Jdsog');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Danila Kisa 78','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Marko','$2a$10$BD.fpKGXtPumfHiNyjGsZulNtD41iXoMFVaVOf7WyQNDu5.kM2cYa','063741256','Markovic','marko','ZyafbNx0Nb2KBeXtD1ZWFqmCnRzxY3cDh7LDYd8dyDVChSc30lNP536zC2qDZJKF');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Kopernikova 12','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Nikolina','$2a$10$RPsiQIMBfCzwxwQfzGWyReLVymMoNXbagtXRsvwj53Qt4vsfyf5NK','0628886407','Pavkovic','nikolina','7zvbyf6e2n9jau8446KnXaYkXnLZuckeG5XiOh7mpQQUHHrD7x1jvrf6GwRzGdfz');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Mise Dimitrijevica 47','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Katarina','$2a$10$9mfSC/ikLTPq3OmZexkCP.zR.QDcWccpaKzoVIBFQde3L.gMWtUBy','062547896','Stojanovic','katarina','iBskyO6bHTygKoicSTuhMvTDf3tWw7O2v9RalG0mHMWyJaxcVArvQzhv7tBzW7Jm');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Tolstojeva 21','Novi Sad','Serbia','isausermailsmn@gmail.com',false, false, true,'Milan','$2a$10$/rEcPhHiY5s/Qjjt1vH50OUc4Zc4Yb1PKQFWlDfVfhkYAfsZLQTGa','068745891','Milanovic','milan','NmbxPgQZ8u6bjEVbAmBstXNbM2TkeeHWMolitNr3NXQ1AwgvSVaxosukczcJDBnE');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Vojvode Supljikca 5','Novi Sad','Serbia','isausermailsmn@gmail.com',false, false, true,'Nadja','$2a$10$R1gtmE5FiSFicg5q1ZArY.yz72jDg6lIG3v4NSDfHQ5x5LeZ6lP9.','063569874','Nedic','nadja','E54heSJ72vKYkSceZHXtTmSkTNrydbC5cALRsiRxFRWUV3sJZG02lDDOlM1odMfZ');
INSERT INTO users VALUES (nextval('user_seq_gen'),'Veselina Maslese 20','Novi Sad','Serbia','isausermailsmn@gmail.com',true, false, true,'Zoran','$2a$10$u043ToE5JwoXsEAgQSsooOynNOX2cJJ4wBSa3adcFp90jXZevPhbu','0637319017','Zoric','zoran','4OdrmXVz6LQrUJrfTxXaaUPVXBPxVC1gyKXqRUYa3UZQa9s5EcvOgWPGOwvz1oKX');

INSERT INTO users_roles VALUES (1,1);
INSERT INTO users_roles VALUES (2,3);
INSERT INTO users_roles VALUES (3,3);
INSERT INTO users_roles VALUES (4,5);
INSERT INTO users_roles VALUES (5,6);
INSERT INTO users_roles VALUES (6,4);
INSERT INTO users_roles VALUES (7,5);
INSERT INTO users_roles VALUES (8,6);
INSERT INTO users_roles VALUES (9,4);
INSERT INTO users_roles VALUES (10,2);

INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 1);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 2);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 3);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 4);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 5);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 6);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 7);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 8);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 9);
INSERT INTO user_category VALUES (nextval('user_category_seq_gen'), 0, 0, 10);

INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 4);
INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 5);
INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 6);
INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 7);
INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 8);
INSERT INTO owner_income VALUES (nextval('owner_income_seq_gen'), 0, 0, 9);

INSERT INTO location VALUES (nextval('location_seq_gen'),'Kralja Petra 5','Novi Sad','Srbija',0,0);
INSERT INTO location VALUES (nextval('location_seq_gen'),'Marko Miljanova 6','Novi Sad','Srbija',0,0);
INSERT INTO location VALUES (nextval('location_seq_gen'),'Gunduliceva 10','Zrenjanin','Srbija',0,0);

INSERT INTO reservation_entity VALUES (nextval('reservation_entity_seq_gen'),0,0,'Cekamo vas!',false,5,'Vikendica kod Milana','',1,8);
INSERT INTO reservation_entity VALUES (nextval('reservation_entity_seq_gen'),0,0,'Cekamo vas da plovimo od Novog Sada do Beograda',false,20,'Plovite sa Kacom','',2,7);
INSERT INTO reservation_entity VALUES (nextval('reservation_entity_seq_gen'),0,0,'Naucite da pecate sa nama!',false,10,'Avantura na Dunavu','',3,9);

INSERT INTO lodge VALUES (1);

INSERT INTO bedroom VALUES (nextval('bedroom_seq_gen'),0,0,1);
INSERT INTO bedroom VALUES (nextval('bedroom_seq_gen'),1,1,1);
INSERT INTO bedroom VALUES (nextval('bedroom_seq_gen'),2,1,1);
INSERT INTO bedroom VALUES (nextval('bedroom_seq_gen'),3,0,1);

-- INSERT INTO image VALUES (nextval('image_seq_gen'),false,'/images/Avantura na Dunavu1.jpg',3);
-- INSERT INTO image VALUES (nextval('image_seq_gen'),false,'/images/Plovite sa Kacom2.jpg',2);
-- INSERT INTO image VALUES (nextval('image_seq_gen'),false,'/images/Vikendica kod Milana3.jpg',1);

INSERT INTO ship VALUES (3,200,50,50,'','recni brod',2);

INSERT INTO adventure VALUES ('Iskusan stari ','Morate poneti svoju opremu',3);

INSERT INTO delete_request VALUES (nextval('delete_request_seq_gen'), 'I just want to exit this platform', false, false, 'zoran', 0);
INSERT INTO delete_request VALUES (nextval('delete_request_seq_gen'), 'No specific reason', false, false, 'nadja', 0);

INSERT INTO account_request VALUES (nextval('account_request_seq_gen'), 'I want to be part of your platform', 'nadja');
INSERT INTO account_request VALUES (nextval('account_request_seq_gen'), 'I want to be part of your platform', 'milan');

INSERT INTO complaint VALUES (nextval('complaint_seq_gen'), false, 'I dont like this', 0, 5, 1);
INSERT INTO complaint VALUES (nextval('complaint_seq_gen'), false, 'I dont like this 2', 0, 5, 2);

INSERT INTO rating VALUES (nextval('rating_seq_gen'), 'Great experience!', 5, false, false, 0, 1, 5);
INSERT INTO rating VALUES (nextval('rating_seq_gen'), 'Bad experience!', 2, false, false, 0, 2, 5);

INSERT INTO reservation VALUES (nextval('reservation_seq_gen'), 'wifi', '2022-06-27 15:10:49.239', true, 5, 250, '', 'regularReservation', '2022-05-27 15:10:49.239', 1, 1);
INSERT INTO reservation VALUES (nextval('reservation_seq_gen'), 'parking', '2022-06-20 15:10:49.239', true, 3, 175, '', 'regularReservation', '2022-06-10 15:10:49.239', 1, 2);

INSERT INTO penalty VALUES (nextval('penalty_seq_gen'), 3, 1);

INSERT INTO subscriber VALUES(nextval('subscriber_seq_gen'), 3, 1);
INSERT INTO subscriber VALUES(nextval('subscriber_seq_gen'), 3, 2);
INSERT INTO subscriber VALUES(nextval('subscriber_seq_gen'), 2, 1);