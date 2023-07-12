
------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO Rola(rola_id, nazwa) VALUES (nextval('rola_sequence'), 'ROLE_USER');
INSERT INTO Rola(rola_id, nazwa) VALUES (nextval('rola_sequence'), 'ROLE_ADMIN');

INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:read');
INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:write');
INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:update');
INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:delete');

INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (1,1);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (2,1);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (3,1);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (4,1);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (1,2);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (2,2);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (3,2);
INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (4,2);

INSERT INTO uzytkownik_aplikacji(uzytkownik_aplikacji_id, password, username) VALUES (nextval('app_uzytkownik_sequence'),'$2a$10$png5w1D77.ZxhGsLk7.EeO0vumPzUCdGY/hoMr.QrP7BC65r8ueQ6', 'test1@zz.pl');
INSERT INTO uzytkownik_aplikacji(uzytkownik_aplikacji_id, password, username) VALUES (nextval('app_uzytkownik_sequence'),'$2a$10$bvYSevQ1YCkHwfGuHW889e1f3VbEhrBYGh.IpGc8iRMbP1iRkFdty', 'test2@zz.pl');

INSERT INTO rola_uzytkownik_aplikacji(rola_id, uzytkownik_aplikacji_id) VALUES (2,1);
INSERT INTO rola_uzytkownik_aplikacji(rola_id, uzytkownik_aplikacji_id) VALUES (1,2);

INSERT INTO uzytkownik(uzytkownik_id, imie, nazwisko, uzytkownik_aplikacji_id) VALUES (nextval('uzytkownik_sequence'),'Konrad', 'Macionski', 1);
INSERT INTO uzytkownik(uzytkownik_id, imie, nazwisko, uzytkownik_aplikacji_id) VALUES (nextval('uzytkownik_sequence'),'Anna', 'Malinowska', 2);

INSERT INTO subskrypcja(subskrypcja_id, uzytkownik_id) VALUES (nextval('subskrypcja_sequence'), 1);

INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp1');
INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp2');
INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp3');
------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO pasieka(pasieka_id, lat, lon, nazwa, predykcja_id, uzytkownik_id) VALUES (nextval('pasieka_sequence'), 55.21, 22.044,'pasieka_Piotr',1, 1);
INSERT INTO pasieka(pasieka_id, lat, lon, nazwa, predykcja_id, uzytkownik_id)  VALUES (nextval('pasieka_sequence'), 56.21, 22.044,'pasieka_Łukasz',1, 1);
INSERT INTO pasieka(pasieka_id, lat, lon, nazwa, predykcja_id, uzytkownik_id)  VALUES (nextval('pasieka_sequence'), 57.21, 24.044,'pasieka_Konrad',2, 2);
INSERT INTO pasieka(pasieka_id, lat, lon, nazwa, predykcja_id, uzytkownik_id)  VALUES (nextval('pasieka_sequence'), 58.21, 25.044,'pasieka_Düsseldorf',null, 1);

INSERT INTO matka_pszczela(matka_pszczela_id, data_wprowadzenia, rodzaj_pozyskania) VALUES (nextval('matka_pszczela_sequence'), '2022-05-03', 'Własna');
INSERT INTO matka_pszczela(matka_pszczela_id, data_wprowadzenia, rodzaj_pozyskania) VALUES (nextval('matka_pszczela_sequence'), '2021-06-13', 'Własna');
INSERT INTO matka_pszczela(matka_pszczela_id, data_wprowadzenia, rodzaj_pozyskania) VALUES (nextval('matka_pszczela_sequence'), '2022-05-21', 'Kupiona od hodowcy XYZ');
INSERT INTO matka_pszczela(matka_pszczela_id, data_wprowadzenia, rodzaj_pozyskania) VALUES (nextval('matka_pszczela_sequence'), '2021-05-21', 'Kupiona od hodowcy ABC');

INSERT INTO waga(waga_id, model, producent, apiID) VALUES (nextval('waga_sequence'), 'pszczelarz1', 'producent1', 'SECRET');
INSERT INTO waga(waga_id, model, producent, apiID) VALUES (nextval('waga_sequence'), 'pszczelarz2', 'producent2', 'SECRET');

INSERT INTO ul(ul_id, nazwa, poziom_agresji, rodzaj_korpusu, rodzaj_ramek, matka_pszczela_id, pasieka_id, waga_id, rasa) VALUES (nextval('ul_sequence'), 'ul1' ,'niski', 'drewniany', 'małe',2,1,1, 'krainka');
INSERT INTO ul(ul_id, nazwa, poziom_agresji, rodzaj_korpusu, rodzaj_ramek, matka_pszczela_id, pasieka_id, waga_id, rasa) VALUES (nextval('ul_sequence'), 'ul2' ,'wysoki', 'stalowy', 'średnie',1,1,NULL,'krainka');
INSERT INTO ul(ul_id, nazwa, poziom_agresji, rodzaj_korpusu, rodzaj_ramek, matka_pszczela_id, pasieka_id, waga_id, rasa) VALUES (nextval('ul_sequence'), 'ul1' ,'średni', 'drewniany', 'duże',3,2,NULL,'krainka');
INSERT INTO ul(ul_id, nazwa, poziom_agresji, rodzaj_korpusu, rodzaj_ramek, matka_pszczela_id, pasieka_id, waga_id, rasa) VALUES (nextval('ul_sequence'), 'StolicaUli' ,'niski', 'stalowy', 'małe',NULL,3,2,'włoszka');
INSERT INTO ul(ul_id, nazwa, poziom_agresji, rodzaj_korpusu, rodzaj_ramek, matka_pszczela_id, pasieka_id, waga_id, rasa) VALUES (nextval('ul_sequence'), 'ulNiemiecki' ,'niski', 'stalowy', 'duże',NULL,4,NULL,'Buckfast');





insert into alert (alert_id, informacja) values (nextval('alert_sequence'), 'Uwaga: Wykryto starą matkę pszczelą');
insert into alert (alert_id, informacja) values (nextval('alert_sequence'), 'Uwaga: Brak matki pszczelej');
insert into alert (alert_id, informacja) values (nextval('alert_sequence'), 'Uwaga: zombie ');




INSERT INTO pokarm(pokarm_id, Rodzaj, Masa) VALUES (nextval('pokarm_sequence'), 'cukier', 6969);


INSERT INTO choroba(choroba_id, aktywna, nazwa, opis)  VALUES (1,true,'Zgnilec amerykański', 'zwany złośliwym');
INSERT INTO choroba(choroba_id, aktywna, nazwa, opis)  VALUES (2,false,'Warroza', 'najgroźniejsza choroba pszczół');
INSERT INTO choroba(choroba_id, aktywna, nazwa, opis)  VALUES (3,true,'Nosemoza', 'zwana też chorobą zarodnikowcową lub sporowcową');

INSERT  INTO choroba_ul values (1,1);
INSERT  INTO choroba_ul values (2,1);
INSERT  INTO choroba_ul values (3,1);

INSERT  INTO choroba_ul values (1,2);
INSERT  INTO choroba_ul values (2,2);
INSERT  INTO choroba_ul values (3,2);





insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);
insert into wspolna_baza_chorob values (nextval('wspolna_baza_chorob_sequence'), '2022-11-5', 52.23106084138371, 21.005693164521702);




insert into dane_wagowe (scales_data_id, date, max_weight, min_weight , waga_id) values (nextval('scales_sequence'), '2022-11-5', 41.12, 0, 1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-6', 41.88, 0, 1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-7', 42.33, 0, 1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-8', 30.33, 0, 1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id)  values (nextval('scales_sequence'), '2022-11-9', 31.33, 0, 1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-10',31.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-11',32.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-12',33.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-13',34.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-14',35.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-15',36.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-17',37.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-17',38.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-18',39.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-19',41.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-20',41.13, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-21',41.43, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-22',22.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-23',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-24',24.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-25',22.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-26',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-27',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-28',24.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-29',21.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-11-30',22.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-01',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-02',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-03',23.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-04',22.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-05',21.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-06',24.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-07',25.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-08',26.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-09',27.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-10',28.83, 0 ,1);
insert into dane_wagowe (scales_data_id, date, max_weight, min_weight, waga_id) values (nextval('scales_sequence'), '2022-12-11',30.83, 0 ,1);






-- create or replace procedure initializeForTests ()
--     language plpgsql
-- as $$
-- begin
--
--     INSERT INTO Rola(rola_id, nazwa) VALUES (nextval('rola_sequence'), 'ROLE_USER');
--     INSERT INTO Rola(rola_id, nazwa) VALUES (nextval('rola_sequence'), 'ROLE_ADMIN');
--
--     INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:read');
--     INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:write');
--     INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:update');
--     INSERT INTO Uprawnienie(uprawnienie_id, nazwa) VALUES (nextval('uprawnienie_sequence'), 'app:delete');
--
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (1,1);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (2,1);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (3,1);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (4,1);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (1,2);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (2,2);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (3,2);
--     INSERT INTO rola_uprawnienie(uprawnienie_id, rola_id) VALUES (4,2);
--
--     INSERT INTO uzytkownik_aplikacji(uzytkownik_aplikacji_id, password, username) VALUES (nextval('app_uzytkownik_sequence'),'$2a$10$png5w1D77.ZxhGsLk7.EeO0vumPzUCdGY/hoMr.QrP7BC65r8ueQ6', 'test1@zz.pl');
--     INSERT INTO uzytkownik_aplikacji(uzytkownik_aplikacji_id, password, username) VALUES (nextval('app_uzytkownik_sequence'),'$2a$10$bvYSevQ1YCkHwfGuHW889e1f3VbEhrBYGh.IpGc8iRMbP1iRkFdty', 'test2@zz.pl');
--
--     INSERT INTO rola_uzytkownik_aplikacji(rola_id, uzytkownik_aplikacji_id) VALUES (2,1);
--     INSERT INTO rola_uzytkownik_aplikacji(rola_id, uzytkownik_aplikacji_id) VALUES (1,2);
--
--     INSERT INTO uzytkownik(uzytkownik_id, imie, nazwisko, uzytkownik_aplikacji_id) VALUES (nextval('uzytkownik_sequence'),'Konrad', 'Macionski', 1);
--     INSERT INTO uzytkownik(uzytkownik_id, imie, nazwisko, uzytkownik_aplikacji_id) VALUES (nextval('uzytkownik_sequence'),'Anna', 'Malinowska', 2);
--
--     INSERT INTO subskrypcja(subskrypcja_id, uzytkownik_id) VALUES (nextval('subskrypcja_sequence'), 1);
--
--     INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp1');
--     INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp2');
--     INSERT INTO predykcja(predykcja_id, wspolczynnik) VALUES (nextval('predykcja_sequence'),'tmp3');
--
-- end;$$;
--
--
--
--
--
-- create or replace procedure clearAllData ()
--     language plpgsql
-- as $$
-- begin
--     delete from wspolna_baza_chorob;
--     delete from dane_wagowe;
--     delete from alert_ul;
--     delete from alert;
--     delete from choroba_ul;
--     delete from choroba;
--     delete from alert_pogodowy;
--     delete from alert_spolecznosciowy;
--     delete from pokarm;
--     delete from subskrypcja;
--     delete from rola_uprawnienie;
--     delete from uprawnienie;
--     delete from rola_uzytkownik_aplikacji;
--     delete from rola;
--     delete from ul;
--     delete from waga;
--     delete from matka_pszczela;
--     delete from pasieka;
--     delete from uzytkownik;
--     delete from uzytkownik_aplikacji;
--     delete from predykcja;
--
--     ALTER SEQUENCE alert_pogodowy_sequence RESTART WITH 1;
--     ALTER SEQUENCE alert_sequence RESTART WITH 1;
--     ALTER SEQUENCE alert_spolecznosciowy_sequence RESTART WITH 1;
--     ALTER SEQUENCE app_uzytkownik_sequence RESTART WITH 1;
--     ALTER SEQUENCE choroba_sequence RESTART WITH 1;
--     ALTER SEQUENCE matka_pszczela_sequence RESTART WITH 1;
--     ALTER SEQUENCE pasieka_sequence RESTART WITH 1;
--     ALTER SEQUENCE pokarm_sequence RESTART WITH 1;
--     ALTER SEQUENCE predykcja_sequence RESTART WITH 1;
--     ALTER SEQUENCE rola_sequence RESTART WITH 1;
--     ALTER SEQUENCE scales_sequence RESTART WITH 1;
--     ALTER SEQUENCE subskrypcja_sequence RESTART WITH 1;
--     ALTER SEQUENCE ul_sequence RESTART WITH 1;
--     ALTER SEQUENCE uprawnienie_sequence RESTART WITH 1;
--     ALTER SEQUENCE uzytkownik_sequence RESTART WITH 1;
--     ALTER SEQUENCE waga_sequence RESTART WITH 1;
--     ALTER SEQUENCE wspolna_baza_chorob_sequence RESTART WITH 1;
--
--
-- end;$$;
