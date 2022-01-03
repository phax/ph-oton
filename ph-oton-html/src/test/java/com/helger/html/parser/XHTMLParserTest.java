/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.html.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLVersion;
import com.helger.html.entity.HTMLEntityResolver;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.serialize.read.SAXReaderSettings;

/**
 * Test class for class {@link XHTMLParser}.
 *
 * @author Philip Helger
 */
public final class XHTMLParserTest
{
  @Test
  public void testLooksLikeHTML ()
  {
    assertTrue (XHTMLParser.looksLikeXHTML ("<abc>"));
    assertTrue (XHTMLParser.looksLikeXHTML ("<a href='any'>"));
    assertTrue (XHTMLParser.looksLikeXHTML (" <strong>"));
    assertTrue (XHTMLParser.looksLikeXHTML ("<a href='any'><strong>what ever</strong></a>"));
    assertTrue (XHTMLParser.looksLikeXHTML ("  <strong>Kolcowój chiński GOJI</strong> (Lycium chinense) \r\n" +
                                            "    <br/>- kolcowój pochodzi z Azji a w naszym klimacie jest w pełni mrozoodporny. \r\n" +
                                            "    <br/>- do roślin psiankowatych, kwiaty są obupłciowe, co oznacza, że roślina jest samopylna. \r\n" +
                                            "    <br/>- nie jest wymagająca w uprawie, rośnie dobrze również na ubogich glebach. W dobrych warunkach i przy dostatku wilgoci odwdzięczy się jednak wielkością i ilością owoców. Odpowiada jej cieplejsze, słoneczne położenie i lżejsza piaszczysto iłowa gleba pH8. \r\n" +
                                            "    <br/>- dorasta do ok. 2,5 m i tworzy długie, zwisające gałęzie, które łatwo się rozchylają. \r\n" +
                                            "    <br/>- wysadza się w odlegości 2-2,5m a okolicę rośliny dobrze jest pokryć korą do mulczowania. \r\n" +
                                            "    <br/>- poleca się w uprawie przycinać krzew tak, by tworzył jakby nagi pień i koronę. W ten sposób zapewnia się łatwiejszy dostęp do rośliny i bezproblemowy zbiór owoców. \r\n" +
                                            "    <br/>- kwitnie stopniowo od czerwca do sierpnia drobnymi, fioletowymi kwiatkami; kwiaty są zapylane przez owady. \r\n" +
                                            "    <br/>- owocami są czerwone jagody o długości 2,5cm. \r\n" +
                                            "    <br/>- roślina owocuje w 2-3 roku po zasadzeniu. \r\n" +
                                            "    <br/>- w tradycyjnej medycynie chińskiej owoce kolcowoju są niezastąpione a używa się ich od stuleci. \r\n" +
                                            "    <br/>- kolcowój coraz częściej jest używany u nas jako suplement diety lub do przygotowania herbaty. \r\n" +
                                            "    <br/>- jeśli nie uprawiamy rośliny z powodu owoców, można jej użyć do pokrycia nieciekawych i nieurodzajnych miejsc oraz ostrych skarp. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Gynostema królewska, ziele nieśmiertelności Jiaogulan</strong> Gynostemma pentaphyllum \r\n" +
                                            "    <br/>- gynostema to pnącze o pięciolapczastych liściach, należącym do tej samej rodziny roślin, co ogórek i melon. \r\n" +
                                            "    <br/>- rośnie dziko szczególnie w południowych rejonach Chin, ostatnio jednak staje się bardzo popularna a jej uprawa szybko rozszerza się w innych regionach. \r\n" +
                                            "    <br/>- jest mrozoodporna, dlatego można ją uprawiać na zewnątrz, jednak w tym przypadku zimą obumierają nadziemne części rośliny. \r\n" +
                                            "    <br/>- wiosną roślina znowu odrasta z kłączy, odpowiada jej słoneczne i półcieniste stanowisko. \r\n" +
                                            "    <br/>- w przypadku uprawy w donicach potrzebuje podpórki, osiąga jednak nawet wtedy znaczny wzrost. \r\n" +
                                            "    <br/>- niekiedy nazywana jest „południowym żeńszeniem“, ponieważ zawiera substancje podobne do tych, którymi chlubi się żeńszeń: nie tylko 85 rodzajów saponinów, których koncentracja jest aż czterokrotnie większa niż w żeńszeniu, ale i pierwiastki śladowe, witaminy, białka i aminokwasy. \r\n" +
                                            "    <br/>- już w minionych stuleciach była uważana za „zioło nieśmiertelności“ i wierzono, że przedłuża życie. \r\n" +
                                            "    <br/>- z powodu aktywnego działania zawartych składników przypisuje się tej roślinie ogromną ilość pozytywnych wpływów na organizm. Substancje te wzmacniają odporność, psychikę, pobudzają energię życiową, likwidują stres, obniżają zmęczenie, wzmacniają czynność serca. Rośliny używa się do stłumienia gorączki, obniżenia poziomu cukru i tłuszczu, do regulacji ciśnienia krwi, zaktywowania metabolizmu. Roślina działa jako skuteczny przeciwutleniacz, wskazuje się ją także jako środek prewencji przeciw rakowi. \r\n" +
                                            "    <br/>- w niektórych prowincjach Chiń przygotowuje się herbatę z liści tej rośliny a tamtejsi obywatele dożywają ponadprzeciętnego wieku. Herbata z gynostemy jest tam spożywana równie intensywnie, jak gdzie indziej na przykład zielona herbata i jest uważana za lek przeciw starzeniu się. \r\n" +
                                            "    <br/>- herbata o delikatnym zapachu lukrecji jest umiarkowanie słodkawa a przygotowuje się ją z suszonych i świeżych liści, które pozostawia się do moczenia min. 3 minuty we wrzącej wodzie (dzienna dawka to maksymalnie 4 filiżanki). \r\n" +
                                            "    <br/>- świeże listki można w małej ilości dodawać do sałatek. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Rokitnik zwyczajny</strong> (Hippophae rhamnoides) \r\n" +
                                            "    <br/>- wielki, opadający, ciernisty krzew, który dorasta do 2-4m wysokości, niekiedy nawet więcej. \r\n" +
                                            "    <br/>- pędy są jasnoszare, liście wąskie i lancetowate, błyszczące i srebrne. \r\n" +
                                            "    <br/>- jest to rozdzielnopłciowa roślina, kwitnąca w drugiej połowie kwietnia. \r\n" +
                                            "    <br/>- do zapylania nie dochodzi poprzez owady, przebiega ono za pomocą wiatru. \r\n" +
                                            "    <br/>- owoc jest owalny, o długości 6-8 mm, pomarańczowy oraz pomarańczowożółty, dojrzałość od połowy sierpnia do poowy września. \r\n" +
                                            "    <br/>- wymaga słonecznych stanowisk, w cieniu prawie nie owocuje. \r\n" +
                                            "    <br/>- krzewy wysadzamy w odległości 2,5m \r\n" +
                                            "    <br/>- rokitnik nie jest wymagający, dobrze rośnie nawet na całkowicie piaszczystych glebach, w pełnym słońcu i w zasolonym terenie, np. przy drogach, nie cierpi na choroby i z powodu szkodników. \r\n" +
                                            "    <br/>- mimo tego, że jest to skromny i dostosowujący się krzew, chcemy przecież uzyskać bogaty plon, dlatego musimy dobrze przygotować glebę przed zasadzeniem. \r\n" +
                                            "    <br/>- wykopać dostatecznie dużą jamę, wprowadzić do niej nawóz organiczny, najlepiej zleżały obornik, a do gleby przy zasypywaniu dodać nawóz fosforowy. \r\n" +
                                            "    <br/>- nie znosi nawożenia azotowego, ponieważ na korzeniach rokitnika żyją grzyby, które wiążą azot z powietrza. \r\n" +
                                            "    <br/>- każdego roku nawozimy nawozami mineralnymi bez azotu a raz na trzy lata wprowadzimy roślinie ok. 10 kg zleżałego obornika. \r\n" +
                                            "    <br/>- jak tylko roślina zaczyna rosnąć, regularnie usuwamy odrosty korzeniowe, które możemy wykorzystać do dalszego wysadzania. \r\n" +
                                            "    <br/>- krzewy wymagają dodatkowego podlewania w okresie suchym, wilgoć musimy zapewnić szczególnie w okresie wzrostu pędów i owoców. \r\n" +
                                            "    <br/>- system korzeniowy rokitnika jest powierzchniowy, więc szkodzi mu głębsze okopywanie, dlatego warto zamulczować glebę. \r\n" +
                                            "    <br/>- krzewy w roku drugim lub trzecim musimy przyciąć tak, aby osiągnąć zwartą, silną koronę, inaczej roślina wyrośnie w górę i będziemy mieć problem ze zbiorem owoców. \r\n" +
                                            "    <br/>- usuwanie gałęzi w dolnej części jest zupełnie naturalne, rośliny przesuwają zieleń do górnych pięter. \r\n" +
                                            "    <br/>- odmładzające cięcie przeprowadza się pod koniec okresu spokoju wegetacyjnego, czyli mniej więcej w połowie marca. \r\n" +
                                            "    <br/>- większe rany cięte należy opatrzyć woskiem do szczepienia. \r\n" +
                                            "    <br/>- owoce są bardzo bogate w witaminę C oraz karoten. \r\n" +
                                            "    <br/>- używa się ich do produkcji napojów/ likier/, dżemów, kompotów, domowego wina... \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Cytryniec chiński, </strong>(Schisandra chinensis) \r\n" +
                                            "    <br/>- pochodzi z Chin, gdzie od wielu stuleci zaliczany jest do najważniejszych roślin leczniczych, zapewniających odnowę sił witalnych. \r\n" +
                                            "    <br/>- skuteczne składniki cytryńców charakteryzują całą roślinę, dlatego można wykorzystywać nie tylko owoce, ale również liście i młode pędy na herbatę. \r\n" +
                                            "    <br/>- cytryniec był długo analizowany w rosyjskich ośrodkach badawczych, następnie zaczęto go używać jako jako środka stymulującego i tonizującego, wyraźnie zwięszającego zdolność do pracy. \r\n" +
                                            "    <br/>- cytryniec posiada następujące pozytywne właściwości: zwiększa psychiczną i fizyczną wydajność, wzmacnia akcję serca, usuwa zmęczenie, bardzo dobrze wpływa na układ oddechowy, pomaga przy cukrzycy, obniża poziom cukru we krwi, chroni przed infekcjami, skutecznie działa przeciw chorobom reumatycznym i dnie moczanowej. \r\n" +
                                            "    <br/>- w przeciwieństwie do innych środków pobudzających nie ma negatywnych skutków ubocznych. \r\n" +
                                            "    <br/>- cytryniec jest światłolubny, w pełni mrozoodporny. Jest roślina pnącą. \r\n" +
                                            "    <br/>- sadzi się go w jamie 60x60cm, doradzamy kwaśną i przepuszczalną glebę. \r\n" +
                                            "    <br/>- jeszcze bardziej niż powojnik wymaga „nóg w chłodzie“ – najlepiej powodzi się mu na osłoniętym stanowisku przy ścianie. Korzenie jednak musimy chronić przed wyschnięciem poprzez nałożenie warstwy mulczu. Najlepiej jest wysadzić wokół cytryńca kilka niskich krzewów, na przykład bukszpanów, które zabronią temu, by ziemia była narażona na bezpośrednie działanie słońca. \r\n" +
                                            "    <br/>- pnie się po podpórce z drutu – tak samo jak winorośle. \r\n" +
                                            "    <br/>- z jednego krzewu poziomo rozciagamy 1-2 pędy, pozostałe jesienią zetniemy. \r\n" +
                                            "    <br/>- roślina owocuje od drugiego roku po zasadzeniu. Owoce wyglądają jak wielkie grona porzeczek. Zapylana jest przez owady, owocuje od września do października. Jagód używa się do przygotowania herbaty, sok ze świeżych owoców z podwójną ilością cukru wytrzyma długi czas, zachowując wszystkie wartości. \r\n" +
                                            "    <br/>- liście i młode pędy zbiera się w sierpniu, rozdrabnia się i suszy, następnie używa do przygotowania herbaty. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>STEWIA, SUGAR BABY</strong> (Stevia rebaudiana) \r\n" +
                                            "    <br/>- wieloletnia roślina z rodziny astrowatych. \r\n" +
                                            "    <br/>- pochodzi z Brazylii i Paragwaju, gdzie jest używana jako naturalny słodzik. \r\n" +
                                            "    <br/>- jest zupełnie pozbawiona kalorii. \r\n" +
                                            "    <br/>- jest wielokrotnie słodsza od cukru, bywa nazywana słodką trawą. \r\n" +
                                            "    <br/>- nie jest mrozoodporna, w naszym klimacie uprawia się ją w pojemnikach jako wieloletnią roślinę, w szklarni albo całorocznie w mieszkaniu. \r\n" +
                                            "    <br/>- latem można wkopać roślinę razem z doniczką do klombu. \r\n" +
                                            "    <br/>- można ją również wysadzać po wstępnej uprawie na zewnętrze, słoneczne lub półcieniste, ciepłe stanowisko. \r\n" +
                                            "    <br/>- zewnętrzne wysadzanie przeprowadzamy dopiero po ostatnich wiosennych przymrozkach. \r\n" +
                                            "    <br/>- powodzi się jej na glebach gliniasto – piaszczystych z dostatkiem powietrza i wody, w glebach z reakcją kwaśną lub neutralną (pH6,5 – 7,5) oraz mniejszą ilością substancji organicznych. \r\n" +
                                            "    <br/>- jedynie korzenie nie zawierają substancji słodzących. \r\n" +
                                            "    <br/>- roślinę można ścinać przed kwitnieniem, lub skrócić przy wysokości do 40 cm o około połowę. \r\n" +
                                            "    <br/>- obficie się rozgałęzia i łatwo odrasta. \r\n" +
                                            "    <br/>- na zimę opada jej większość liści a zimuje jedynie bryła korzeniowa posadzona w umiarkowanie wilgotnym substracie. \r\n" +
                                            "    <br/>- zimuje w temperaturze 5 – 10°C i przy bardzo ograniczonym podlewaniu (jedynie tak, aby substrat pozostał wilgotny). \r\n" +
                                            "    <br/>- rozmnaża się wegetatywnie – poprzez dzielenie rośliny lub z pomocą odrostów. \r\n" +
                                            "    <br/>- jej słodkość wzmacnia się wraz długością i intensywnością światła słonecznego, w przypadku uprawy jako rośliny pokojowej dobrze jest ją umieścić na oknie południowym. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>ARONIA CZARNOOWOCOWA</strong> (Aronie melanocarpa) \r\n" +
                                            "    <br/>- chodzi o gęsty krzew w dużej wartości ozdobnej i wysokim plonie owoców. \r\n" +
                                            "    <br/>- aronia jest samopylna i płodzi obficie już w pierwszych latach po zasadzeniu. \r\n" +
                                            "    <br/>- przeznaczona jest do wysadzania na stanowiskach słonecznych i dorasta do wielkości ok. 1,5 m \r\n" +
                                            "    <br/>- ponieważ jesienią zabarwia się wspaniale na purpurowoczerwony kolor, można ją wykorzystać również w ogrodach ozdobnych jako soliter, albo jako element żywopłotów. \r\n" +
                                            "    <br/>- nie jest konieczne kształtowanie, ponieważ rośnie naturalnie spójnie. \r\n" +
                                            "    <br/>- krzew ten jest niewymagający zarówno co do gleby jak i do pielęgnacji, jest absolutnie odporny na zimno, nie cierpi z powodu chorób i szkodników. \r\n" +
                                            "    <br/>- kwitnie na końcach gałęzi od kwietnia do maja. \r\n" +
                                            "    <br/>- pod koniec lata dojrzewają wielkie, smaczne owoce nadające się do szerokiego wykorzystania, zbiera się je wtedy, gdy są miękkie i soczyste. \r\n" +
                                            "    <br/>- owoce są bardzo zdrowe i bogate w witaminy. \r\n" +
                                            "    <br/>- zawierają mało kwasów, dużo cukrów i bardzo intensywny, czerwonoczarny barwnik. \r\n" +
                                            "    <br/>- znajdują szerokie zastosowanie nie tylko w osobno przy produkcji różnych soków, dżemów, ale też można je dodać do innych owoców, czym wzbogaca się smak. \r\n" +
                                            "    <br/>- sok można wykorzystać jako naturalny barwnik dodawany na przykład przy wyrobie win. \r\n" +
                                            "    <br/>- sok owocowy jest smaczniejszy, jeśli zmiesza się go z innym kwaśniejszym sokiem innych owoców. \r\n" +
                                            "    <br/>- smaczne są również marmolady z mieszanką owoców aronii i innych owoców. \r\n" +
                                            "    <br/>- owocom aronii przypisuje się dobroczynne działanie przy wysokim ciśnieniu krwi i przy arteriosklerozie, wyższym poziomie cholesterolu, dla wzmocnienia odporności i polepszenia obiegu krwi. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Melon gruszkowaty \"Pepino Gold\"</strong> (Solanum muricatum) \r\n" +
                                            "    <br/>- Melon na balkony i tarasy. \r\n" +
                                            "    <br/>- Pepino Gold możesz uprawiać w wiszącej doniczce albo w donicy. \r\n" +
                                            "    <br/>- Od połowy maja do pierwszych przymrozków możesz pozostawić roślinę na zewnątrz. \r\n" +
                                            "    <br/>- Używaj dobrej ziemi ogrodowej i nawoź regularnie. \r\n" +
                                            "    <br/>- Owoce tworzą się z małych, fioletowych kwiatów i mają słodki smak przypominający miodowy melon z delikatnym gruszkowym posmakiem. \r\n" +
                                            "    <br/>- Po zebraniu owoców rośline zetnij i pozostaw w bezmroźnym, jasnym pomieszczeniu. W czasie zimowania podlewaj jedynie minimalnie, aby ziemia całkiem nie wyschła. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Miechunka peruwiańska</strong> (Physalis peruviana) \r\n" +
                                            "    <br/>- Jednoletnia roslina pochodząca z Ameryki Południowej (krewna miechunki rozdętej). \r\n" +
                                            "    <br/>- Ta oto odmiana jadalna dorasta do wys. ok. 50 cm. Bardzo wczesna odmiana o przyjemnym słodkokwaśnym smaku. \r\n" +
                                            "    <br/>- Powodzi się jej w słonecznym miejscu, w glebach gliniasto-piaszczystych, bogatych w próchnicę oraz w dostatek substancji odżywczych. \r\n" +
                                            "    <br/>- Sadzi się ją w rozsadzie 7x7 cm. \r\n" +
                                            "    <br/>- Uprawia się podobnie jak pomidory. \r\n" +
                                            "    <br/>- Wysadza się po przeduprawie. \r\n" +
                                            "    <br/>- Ponieważ jest podatna na uszkodzenia spowodowane przez mróz, do gruntu sadzimy ją aż od drugiej połowy maja. \r\n" +
                                            "    <br/>- Dobra temperatura dla wzrostu to ok. 25°C. \r\n" +
                                            "    <br/>- Nie cierpi na choroby ani z powodu szkodników. \r\n" +
                                            "    <br/>- Po przekwitnięciu tworzy się kielich, który podczas dojrzewania zasycha i pęka. \r\n" +
                                            "    <br/>- Skrywa on jagodę w wielkości ok. 1 cm. \r\n" +
                                            "    <br/>- Owoce zbieramy, gdy są w pełni dojrzałe (żółte) i z kielichem, od sierpnia do października. \r\n" +
                                            "    <br/>- Zebrane przed mrozem niedojrzałe owoce w czasie przechowywania dojrzeją w pełni \r\n" +
                                            "    <br/>- Składowane w niskiej temperaturze wytrzymają nawet kilka miesięcy. \r\n" +
                                            "    <br/>- Spożywa się je świeże. \r\n" +
                                            "    <br/>- Nadają się do sałatek, można je suszyć i przetworzyć w przetworach. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Czosnek niedźwiedzi</strong> (Allium ursinum) \r\n" +
                                            "    <br/>- Bylina, która w naszych warunkach przezimuje bezpiecznie . \r\n" +
                                            "    <br/>- Przy uprawie wymaga stanowiska w cieniu lub w półcieniu, nadaje się jako runo pod drzewa lub krzewy. \r\n" +
                                            "    <br/>- Cebulki rozrastają się i łatwo i obficie . \r\n" +
                                            "    <br/>- Z cebulek wyrastają wczesną wiosną kępki liści a roślina osiąga wielkość 20-50 cm. \r\n" +
                                            "    <br/>- Kwiaty pojawiają się od kwietnia do czerwca, po przekwitnięciu ponownie wciąga się do cebulki. \r\n" +
                                            "    <br/>- Nie wymaga żadnej specjalnej opieki. \r\n" +
                                            "    <br/>- Roślina wyraźnie pachnie i smakuje jak czosnek, po użyciu jednak nie pozostawia typowego aromatu. \r\n" +
                                            "    <br/>- Do wzbogacenia jadłospisu można wykorzystywać liście i kwiaty. \r\n" +
                                            "    <br/>- Zawartość substancji odżywczych jest o wiele wyższa niż u czosnku. \r\n" +
                                            "    <br/>- Działa prewencyjnie przeciw chorobom winikającym z przeziębienia, obniża poziom cholesterolu, działa na obniżenie ciśnienia krwi, spowalnia zapalenia, oczyszcza organizm. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Tulbagia fioletowa</strong> (Tulbaghia violacea) \r\n" +
                                            "    <br/>- Nie jest odporna na zimno i wymaga słonecznego stanowiska (parapet okienny, taras, ewentualnie można zakopać roślinę w doniczce w klombie ustawionym do słońca. \r\n" +
                                            "    <br/>- Zbierać można całorocznie, podobnie jak szczypiorek. \r\n" +
                                            "    <br/>- Używamy do potraw z mięsa, makaranów, do sosów, dresingów, do doprawiania past i sałatek, marynowania itd. \r\n" +
                                            "    <br/>- Drobno posiekane źdźbła można zamrozić, później przed użyciem łatwo odetniemy nożem potrzebną ilość. \r\n" +
                                            "    <br/>- Olej z tulbagii fioletowej: 150 g drobno posiekanych ździebeł zalejemy 0,75 l oliwy z oliwek. \r\n" +
                                            "    <br/>- Jest idealny do każdodziennego, szybkiego wykorzystania. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Bazylia pospolita, Kilimandżaro</strong> (Ocimum kiliman. basilicum) \r\n" +
                                            "    <br/>- Należy do absolutnie najpopularniejszych ziół! \r\n" +
                                            "    <br/>- Bardzo silna, długoletnia, bujnie rosnąca odmiana bazylii. \r\n" +
                                            "    <br/>- Motyle są wabione do ogrodu wielością niebieskofioletowych kwiatów tej byliny. \r\n" +
                                            "    <br/>- Aby bazylia nie straciła swojego aromatu, dodawaj ją dopiero ściśle przed końcem gotowania. \r\n" +
                                            "    <br/>- Kwiaty i liście możesz wykorzystać zarówno do przygotowywania ryb i potraw mięsnych, jak i do warzyw, serów i sałatek. \r\n" +
                                            "    <br/>- Szczególnie rewelacyjnie komponuje się z Mozzarellą. \r\n" +
                                            "    <br/>- Bazylia potrzebuje ciepłego stanowiska i gleby gliniasto-piaszczystej oraz dostatecznej wilgoci. \r\n" +
                                            "    <br/>- Nie jest odporna na zimno. \r\n" +
                                            "    <br/>- Najlepiej jest uprawiać ją w większej doniczce, umieszczając na zewnątrz w sezonie letnim. \r\n" +
                                            "    <br/>- Regularnie (mniej więcej co 4-6 tygodni) potrzebuje płynnego nawozu. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Wilec ziemniaczany</strong> \r\n" +
                                            "    <br/>- Słodkie ziemniaki – bataty \r\n" +
                                            "    <br/>- Potrzebują słonecznego lub półcienistego stanowiska oraz lekkiej, dobrze przepuszczającej gleby. \r\n" +
                                            "    <br/>- Nawozić należy co trzy tygodnie nawozem z dodatkiem masy rogowej. \r\n" +
                                            "    <br/>- W ciągu lata tworzą się bulwy-córki, które zbiera się jesienią przed pierwszymi przymrozkami. \r\n" +
                                            "    <br/>- Bulwy składujemy w chłodnym, suchym pomieszczeniu. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Tytoń szlachetny</strong> (Nicotiana tabacum) \r\n" +
                                            "    <br/>- Zbiór własnego tytoniu! \r\n" +
                                            "    <br/>- Jednoroczna roślina dorastająca do wysokości 2 - 2,5 m. \r\n" +
                                            "    <br/>- Wymaga słonecznych, osłoniętych stanowisk, lekkich gliniasto-piaszczystych gleb (dostatek próchnicy, wyższa zawartość potasu, częste spulchnianie). \r\n" +
                                            "    <br/>- Uwaga na chłód i przemoczenie! \r\n" +
                                            "    <br/>- Podlewamy tylko korzenie! \r\n" +
                                            "    <br/>- Wysadzamy od połowy maja, w odległości ok. 1 m. \r\n" +
                                            "    <br/>- Liście zbiera się stopniowo od spodu i suszy się zawieszone w suchym i napowietrzonym miejscu. \r\n" +
                                            "    <br/>- Nie jest przeznaczony do spożycia! \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Przytulia wonna, marzanka wonna</strong> (Galium odoratum) \r\n" +
                                            "    <br/>- Tworzy zachwycający, zielony dywan pod drzewami! \r\n" +
                                            "    <br/>- Przytulia wonna ma wielostronne zastosowanie. \r\n" +
                                            "    <br/>- Jest znana szczególnie jako dodatek do bowli, suszy się ją również do herbat. \r\n" +
                                            "    <br/>- W medycynie naturalnej jest popularna jako środek zdolny wesprzeć szybko ukrwwienie organizmu. \r\n" +
                                            "    <br/>- Działa przeciw bólom głowy, migrenie i bezsenności. \r\n" +
                                            "    <br/>- Aromatyczny zapach rozwija się dopiero po rozdrobnieniu liści. \r\n" +
                                            "    <br/>- Przytulia wonna jest dekoracyjną, mrozoodporną i okrywową byliną. \r\n" +
                                            "    <br/>- Woli półcieniste stanowiska i wilgotną glebę. \r\n" +
                                            "    <br/>- Można ją także uprawiać w pojemnikach lub w skrzynkach balkonowych. \r\n" +
                                            "    <br/>- Uraduje Cię wówczas wdzięcznymi zwisami. \r\n" +
                                            "    <br/>- Kwitnie od połowy kwietnia do początku maja, pokazując delikatne kwiatki. \r\n" +
                                            "    <br/>- Zbiera się pędy o długości 5-10 cm, zawsze bez kwiatów. \r\n" +
                                            "    <br/>- Odległość przy sadzeniu: 35 x 35 cm. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Mrozoodporny bananowiec</strong> (Musa basjoo) \r\n" +
                                            "    <br/>- Jeśli dobrze przygotuje się go na zimę, można go uprawiać cały rok w gruncie. \r\n" +
                                            "    <br/>- Wymaga chronionego, słonecznego i ciepłego stanowiska, przepuszczającej i spulchnionej gleby z dodatkiem nawozu, który działa długo, stopniowo uwalniając potrzebne składniki odżywcze. \r\n" +
                                            "    <br/>- Gleba w okresie wegetacji nie ma prawa wyschnąć albo być trwale przemoczona! \r\n" +
                                            "    <br/>- Po pierwszych mrozach usuwamy z rośliny obumarłe, brązowe liście, pozostawiając pusty „pseudopień“ rośliny. \r\n" +
                                            "    <br/>- Roślinę należy ochronić przed przemarznięciem. \r\n" +
                                            "    <br/>- Pomysły na prostą osłonę: można obłożyć słomą, następnie jutą, albo można utworzyć konstrukcję, którą wypełnimy liśćmi. \r\n" +
                                            "    <br/>- Warstwa izolacyjna o grubości ok. 30 cm musi być na całej długości kłącza. \r\n" +
                                            "    <br/>- Trzeba ją zabezpieczyć przed przenikaniem wilgoci (pień jest pusty, dlatego nie można pozwolić na wniknięcie wilgoci ze śniegu i deszczu). \r\n" +
                                            "    <br/>- Inną możliwością przezimowania rośliny na zewnątrz jest całkowite usunięcie nadziemnych części pierwotnej rośliny przy samej ziemi. \r\n" +
                                            "    <br/>- W tym przypadku chronimy warstwą izolacji jedynie trwałe podziemne kłącza, z których bananowiec odrośnie w przyszłym roku, tworząc nowe pędy. \r\n" +
                                            "    <br/>- Konieczna warstwa ochronna musi mieć minimum 40 cm wysokości a szerokość najlepiej o kilka centymetrów większą niż szerokość pierwotnego krzewu. \r\n" +
                                            "    <br/>- Można wykorzystać suche liście lub słomę, które trzeba dodatkowo ochronić przed wilgocią. \r\n" +
                                            "    <br/>- Około 2/3 zamulczowanej powierzchni należy zakryć nieprzemakalnym materiałem, ale nie folią. \r\n" +
                                            "    <br/>- Wiosną powoli usuwamy ochronę. \r\n" +
                                            "    <br/>- Jeśli istnieje ryzyko późnych przymrozków, można nowo wyrośnięte pędy na krótko okryć ponownie warstwą mulczu, aby nie doszło do ich uszkodzenia. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Yacon</strong> (Smallanthus sonchifolius) \r\n" +
                                            "    <br/>- Ziemniak diabetyków. \r\n" +
                                            "    <br/>- Roślina liściasta dorastająca nawet do 3 m wysokości. \r\n" +
                                            "    <br/>- W naszych warunkach uprawiana jako jednoroczna (możliwość wysadzenia bulw w następnym roku). \r\n" +
                                            "    <br/>- Ziemia powinna być gliniasto – piaszczysta, lekka. \r\n" +
                                            "    <br/>- Uprawa jest podobna jak w przypadku ziemniaków (nie jest jednak dobrze sadzić w ziemi świeżo nawożonej obornikiem). \r\n" +
                                            "    <br/>- Wymaga przestrzeni. \r\n" +
                                            "    <br/>- Sadzimy w rozsadzie 60 x 80 w pierwszej połowie maja. \r\n" +
                                            "    <br/>- Roślina jest wrażliwa na mróz, dlatego z sadzeniem trzeba poczekać aż do ostatnich przymrozków. \r\n" +
                                            "    <br/>- Tworzy dwa rodzaje bulw: pod powierzchnią znajdują się bulwy łodygowe (tylko do romnażania), niżej zaś zapasowe bulwy (do konsumpcji). \r\n" +
                                            "    <br/>- Zbieramy je późną jesienią, wtedy, gdy nadziemna część przemarznie. \r\n" +
                                            "    <br/>- Potem pozostawiamy je na ok. 2 dni na słońcu w foliowniku lub za oknem, by dojrzały. \r\n" +
                                            "    <br/>- Staną się fioletowe i słodkie. \r\n" +
                                            "    <br/>- Nieuszkodzone bulwy przechowujemy w temperaturze ok. 8 – 10°C, w wilgotnym otoczeniu. \r\n" +
                                            "    <br/>- Można używać je przez całą zimę. \r\n" +
                                            "    <br/>- Spożywa się obrane ze skórki, surowe, gotowane albo smażone. \r\n" +
                                            "    <br/>- Można także pokroić yacon na plasterki i ususzyć. \r\n" +
                                            "    <br/>- Polecana dzienna dawka to 10 dkg. \r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/><strong>Trawa cytrynowa</strong> (Cymbopogon citratus) \r\n" +
                                            "    <br/>- Dla miłośników kuchni azjatyckiej ta roślina jest niezbędna! \r\n" +
                                            "    <br/>- W Indiach uprawia się trawę cytrynową jako zioło lecznicze oraz wykorzystuje w produkcji perfum. \r\n" +
                                            "    <br/>- Duże znaczenie ma też w farmakologii, ponieważ wspiera koncentrację i funkcje mózgowe oraz pobudza czynności organizmu. \r\n" +
                                            "    <br/>- Z długich liści (używamy zawsze tylko świeżych, ponieważ szybko tracą aromat) przygotowuje się herbatę. \r\n" +
                                            "    <br/>- W kuchni wykorzystuje się soczyste łodyżki i liście. Znakomicie pasuje do drobiu, ryb, owoców morza i do zup. \r\n" +
                                            "    <br/>- Grube łodygi aromatyzują potrawy podczas gotowania; pod koniec gotowania wyjmujemy je, nie służą do jedzenia. \r\n" +
                                            "    <br/>- Trawa cytrynowa jest wieloletnia, ale nie mrozoodporna. \r\n" +
                                            "    <br/>- Najlepiej jest ją uprawiać w większej doniczce. \r\n" +
                                            "    <br/>- Latem na zewnątrz, zimą przenosimy do pomieszczenia. \r\n" +
                                            "    <br/>- Zniesie jednak temperaturę do 5°C. \r\n" +
                                            "    <br/>- Podlewamy regularnie oraz nawozimy nawozem płynnym.&nbsp;\r\n" +
                                            "    <br/>\r\n" +
                                            "    <br/>\r\n" +
                                            "  \r\n" +
                                            "  <strong>Asymina trójklapowa ‘Paw-Paw’\r\n" +
                                            "      <br/>-</strong> Asimina triloba. Owoce tego drzewa są walcowato wydłużone i mogą \r\n" +
                                            "  \r\n" +
                                            "  osiągać długość nawet 20 cm oraz ciężar 300 g. Kształtem, smakiem i \r\n" +
                                            "  \r\n" +
                                            "  intensywnym zapachem przypominają banany z domieszką mango, ananasa i wanilii.\r\n" +
                                            "    <br/>- \r\n" +
                                            "  \r\n" +
                                            "  Warunki, jakie trzeba zapewnić tej roślinie, są do spełnienia w \r\n" +
                                            "  \r\n" +
                                            "  naszym klimacie, jeśli postaramy się ją umieścić na odpowiednim \r\n" +
                                            "  \r\n" +
                                            "  stanowisku. \r\n" +
                                            "    <br/>- W okresie spokoju wegetacyjnego przeżyje mróz do ponad -20°C. \r\n" +
                                            "    <br/>- Rośnie powoli i dorasta do wysokości ok. 3 m, dlatego nadaje się również do mniejszych ogródków, gdzie jej tropikalny wygląd przykuwa uwagę. \r\n" +
                                            "    <br/>- Młodej rośliny nie wystawiajmy jednak na bezpośrednie słońce. \r\n" +
                                            "    <br/>- Wysadzamy do przygotowanej próchniczej gleby z dostatkiem wody i substancji odżywczych. \r\n" +
                                            "    <br/>- Za pomocą cięcia utrzymujemy kształt i wielkość korony. \r\n" +
                                            "    <br/>- Pędy wypuszcza późno, dlatego nie grożą jej wiosenne przymrozki. \r\n" +
                                            "    <br/>- Zaczyna kwitnąć jeszcze przed wypuszczeniem pędów, mniej więcej w połowie kwietnia i rozkwita \r\n" +
                                            "  \r\n" +
                                            "  sukcesywnie. \r\n" +
                                            "    <br/>- \r\n" +
                                            "  \r\n" +
                                            "  Kwiaty utrzymują się na roślinie ok. 3 tygodni.\r\n" +
                                            "    <br/>"));
    assertFalse (XHTMLParser.looksLikeXHTML ("a < b"));
    assertFalse (XHTMLParser.looksLikeXHTML ("This is surely not XHTML :)"));
  }

  @Test
  public void testUnescapeXHTML ()
  {
    final XHTMLParser aParser = new XHTMLParser (EHTMLVersion.XHTML11);
    assertNotNull (aParser.unescapeXHTMLFragment ("<b>Hallo</b>"));
    assertNotNull (aParser.unescapeXHTMLFragment ("<b>Hallo<br/>helger</b>"));
    assertNotNull (aParser.unescapeXHTMLFragment (""));
    assertNotNull (aParser.unescapeXHTMLFragment ("&lt;"));
    assertNotNull (aParser.unescapeXHTMLFragment ("&gt;"));
    assertNotNull (aParser.unescapeXHTMLFragment ("<br/>"));
    assertNull (aParser.unescapeXHTMLFragment ("<b>Hallo"));
    assertNull (aParser.unescapeXHTMLFragment ("Hallo</b>"));
    assertNull (aParser.unescapeXHTMLFragment ("&"));
  }

  @Test
  public void testReadFromFile ()
  {
    final IReadableResource aRes = new ClassPathResource ("html-test/test1.htm");
    IMicroDocument aDoc = MicroReader.readMicroXML (aRes, new SAXReaderSettings ().setEntityResolver (HTMLEntityResolver.getInstance ()));
    assertNotNull (aDoc);
    if (false)
    {
      // May fail if in offline mode
      aDoc = MicroReader.readMicroXML (aRes);
      assertNull (aDoc);
    }
  }

  @Test
  @Ignore ("Depends heavily on used JDK version. Fails with 1.6.0_32")
  public void testEntityExpansionLimit ()
  {
    // The XML with too many entities problem
    final String sXML = StringHelper.getRepeated ("&lt;", 100001);

    final XHTMLParser aParser = new XHTMLParser (EHTMLVersion.XHTML11);
    assertNull (aParser.unescapeXHTMLFragment (sXML));
  }
}
