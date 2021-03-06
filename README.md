Ranggo - рејтинг на славни личности
===================================

Содржина:
---------

<ol>
<li><a href="#readme-section-1-intro">Вовед</a></li>
<li><a href="#readme-section-2-requirements">Потребен софтвер</a></li>
<li><a href="#readme-section-3-aggregator">Користење на агрегаторот</a></li>
<li><a href="#readme-section-4-webapp">Поставување на серверскиот дел</a></li>
<li><a href="#readme-section-5-frontend">Работа со клиентскиот дел</a></li>
<li><a href="#readme-section-6-repository">Забелешки за GitHub репозиториумот</a></li>
</ol>

<h2 id="readme-section-1-intro">1. Вовед</h2>

Ranggo е софтвер за рејтинг на славни личности преку семантичко анализирање на статии објавени од влијателни медиуми на нивните портали и содржини објавени на социјалните мрежи. Софтверот агрегира и клучни настани кои ги засегаат славните личности и директно влијаат на нивниот рејтинг.

Проектот се состои од три одделни компоненти кои работат во согласност:

- Агрегатор - агрегирање податоци и нивна анализа;
- Веб апликација / серверски дел - повлекување на веќе анализирани податоци од податочен склад (Spring MVC / MongoDB);
- Веб апликација / клиентски дел - пребарување и прикажување на податоци (AngularJS).

<h2 id="readme-section-2-requirements">2. Потребен софтвер</h2>
За користење на проектот во целина, т.е. на сите негови модули, потребно е претходно да го инсталирате следниот софтвер:

+ GitHub Desktop - алатки за работа со репозиториумот и git shell потребен за извршување на клиентскиот дел на веб апликацијата ([превземи](https://desktop.github.com/))
+ Eclipse for Java EE - работна околина за агрегаторот и серверскиот дел на веб апликацијата ([превземи](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars2))
+ IntelliJ IDEA - работна околина за клиентскиот дел на веб апликацијата ([превземи](https://www.jetbrains.com/webstorm/))
+ Apache Tomcat 8 - серверот на кој се извршува серверскиот дел на веб апликацијата ([превземи](http://tomcat.apache.org/download-80.cgi))
+ Node.js - JavaScript извршна околина потребна за извршување на клиентскиот дел ([превземи](https://nodejs.org/en/download/))
+ MongoDB - податочен склад кој го користат агрегаторот и серверскиот дел на веб апликацијата ([превземи](https://www.mongodb.org/downloads))

<h2 id="readme-section-3-aggregator">3. Користење на агрегаторот</h2>
Агрегаторот претставува Eclipse / Maven проект во Java.

**Инструкции:**

1. Во Eclipse IDE одберете `File > Import...`. Во листата `General` селектирајте `Existing Projects into Workspace`. Притиснете `Next >` и посочете ја локацијата на `aggregator` директориумот во клонираниот репозиториум.
2. Поставете го клучот од вашата лиценца за AlchemyAPI како вредност на `alchemyapi.key` својството во документот `src\main\resources\properties\alchemyapi.properties` (во спротивно не може да се врши семантичка анализа на податоците).
3. Стартувајте го проектот од `Project Explorer` со `Right Click > Run As > Java Application`.

### MongoDB

Пред стартување на проектот, потребно е MongoDB сервер да работи на `host:port` комбинацијата зададена во документот `src/main/resources/properties/data-access.properties`.

MongoDB серверот се стартува со извршување на `mongod` егзекутабилата (се наоѓа во `bin` директориумот на инсталацијата - оваа локација може да се додаде во `PATH` променливата на системската околина). Командата се повикува со параметар за локацијата на податочниот склад - ова не е инсталациониот директориум, туку локацијата на која податоците се зачувуваат физички, на пример:

`mongod --dbpath D:\MongoDB`

Официјално упатство за MongoDB: https://docs.mongodb.org

<h2 id="readme-section-4-webapp">4. Поставување на серверскиот дел</h2>

Серверскиот дел на веб апликацијата претставува Eclipse / Maven webapp проект изграден со Spring MVC рамката за развој.

**Инструкции:**

1. Во Eclipse IDE одберете `File > Import...`. Во листата `General` селектирајте `Existing Projects into Workspace`. Притиснете `Next >` и посочете ја локацијата на `webapp` директориумот во клонираниот репозиториум.
2. Во `Properties` на проектот селектирајте `Project Facets` од листата, и по тоа кликнете на табот `Runtimes`. Штиклирајте го checkbox-от за `Apache Tomcat v8.0`. Притиснете `Apply` и `Ok`.
   
   Доколку нема опција за `Apache Tomcat v8.0` во `Runtimes` на прозорецот, потребно е да се додаде `Tomcat` во листата на сервери на `Eclipse`. Ова може да се направи во `Window > Preferences`. Отворете ја подлистата на `Server` елементот во листата на левата страна и селектирајте `Runtime Environments`. Притиснете `Add...` и од листата Apache одберете `Apache Tomcat v8.0`. Притиснете `Next...` и посочете ја локацијата каде што е инсталиран `Apache Tomcat 8`.

3. (Незадолжително:) поддесете го веб модулот на `Tomcat` за проектот да се извршува во контекстот корен (наместо на `http://localhost/ranggo/` проектот да се извршува на `http://localhost/`). Со двоен клик на `Tomcat v8.0` серверот во `Servers` прозорецот (`Window > Show View > Servers`) се отвара приказ на серверот. Во табот `Modules` селектирајте го `Ranggo` и притиснете `Edit...` (доколку `Ranggo` не е на листата, најпрво стартувајте го проектот според упатството во следната секција). Полето `Path` треба да биде празно. Притиснете `Ok`.
4. Стартувајте го проектот од `Project Explorer` со `Right Click > Run As > Run on Server`.

   Доколку го стартувате проектот за прв пат, од листата на дијалог прозорецот одберете го серверот `Tomcat v8.0 Server`,  штиклирајте `Always use this server when running this project` и притиснете `Next >`. Додадете го `Ranggo` во листата `Configured` доколку веќе не е додаден и притиснете `Finish`.

   Напомена: пред стартување на серверскиот дел на веб апликацијата, потребно е да работи MongoDB сервер како што е специфицирано во делот за MongoDB во секцијата за користење на агрегаторот.

<h2 id="readme-section-5-frontend">5. Работа со клиентскиот дел</h2>

Клиентскиот дел на веб апликацијата претставува IntelliJ IDEA проект, и истиот е изграден со AngularJS.

**Инструкции:**

1. Извршете ги следните команди во терминал во основниот директориум на клиентскиот дел на веб апликацијата, т.е. `clientapp` директориумот:

   `npm install -g bower`
   
   `npm install`
   
   `bower install`
   
   Доколку не можете успешно да извршите било која команда од обичниот терминал на оперативниот систем, можете да го користите Git Shell кој се инсталира во пакет со GitHub Desktop алатките.
   
2. Отворете го проектот во IntelliJ IDEA со `File > Open` и создадете GulpJS конфигурација во прозорецот `Run > Edit Configurations...` со кликнување на зелениот плус во горе лево во прозорецот доколку конфигурацијата веќе не постои. Доколку во прозорецот нема GulpJS опција, потребно е најпрво да се инсталира NodeJS plugin за IntelliJ IDEA. Полето `Name` треба да добие вредност `gulp`, `Gulpfile` треба да ја добие локацијата на gulpfile документот во проектот (`clientapp\gulpfile.js`), `Node interpreter` треба да посочува до `node.exe` - главната егзекутабила на NodeJS (нејзина вообичаена инсталациона локација е `C:\Program Files\nodejs\node.exe`), a `Gulp package` да ја има локацијата на `clientapp\node_modules\gulp` директориумот.
3. Од терминалот извршете ја следната команда во `clientapp` директориумот:
   `gulp build`
   
   Доколку `gulp` командата не е препознаена, најпрво извршете `npm install --global gulp-cli`.
   
4. Стартувајте го gulp серверот во IntelliJ IDEA со `Run > Run 'gulp'`.

   За клиентскиот дел да работи потребно е во позадина да се извршува серверскиот дел на веб апликацијата, а за да се прикажува рејтинг на славни личности, пред тоа мора да се изврши агрегаторот користејќи ја истата база на податоци во податочниот склад која ќе ја користи серверскиот дел на веб апликацијата.

<h2 id="readme-section-6-repository">6. Забелешки за GitHub репозиториумот</h2>

GitHub е хостинг сервис за Git репозиториуми кој овозможува дистрибуирана контрола на промени. Секој соработник на репозиториумот може да прави промени на истиот. По појавувањето на промени во локалниот клониран репозиториум, GitHub Desktop алатката овозможува `commit` операција за зачувување на промените во нова верзија. За промените да се зачуваат во овој репозиториум и да бидат видливи за сите соработници, потребно е да се изврши `sync` на новиот `commit`.

При работењето со Git репозиториум, често се појавуваат конфликти - нееднаквости на документи во вашиот локален репозиториум со соодветните документи во овој репозиториум. Конфликти можат да се појават бидејќи друг соработник извршил `push` на сопствен `commit` по вашиот последен `sync`. За разрешување на конфликтите, потребно е најпрво да извршите `pull` (`sync` во GitHub Desktop алатката) за да се повлечат промените од овој репозиториум, а потоа да ги направите вашите промени и да извршите `commit` и `sync`. Ова се прави за да не се пребришуваат промените на другите соработници, но при тоа внимавајте при `sync` да не ги изгубите локалните промени.

Забелешка: поради природата на агрегаторот и серверскиот дел на веб апликацијата, конфликтите во нивниот `/target` поддиректориум можат да се игнорираат (можете да се врши презапишување на содржината на тој директориум). Дополнително, промените на документите во `src/main/resources/properties` директориумот во склоп на агрегаторот и серверскиот дел на веб апликацијата не треба да се синхронизираат бидејќи истите се користат за лична конфигурација на соодветните проекти.

Упатство за работа со Git: http://rogerdudler.github.io/git-guide/