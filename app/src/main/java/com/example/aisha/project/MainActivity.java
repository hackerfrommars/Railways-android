package com.example.aisha.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText fromStation;
    EditText toStation;
    EditText date;
    Button search_btn;
    String [] station_name = {"Казалинск","Казанбасы","Казахская","Казахстан","Казыбек бек","Каиыр","Кайдауыл","Кайракты","Кайранкуль","Кайрат","Кайыр","Калагир","Калаш","Калкаман","Калмаккырган","Кальпе","Камыссай","Камышлыбаш","Кандыагаш","Капчагай","Кара-Адыр","Карабас","Карабатано","Карагай","Карагайлы","Караганда сорт","Караганозек","Каражал","Кара-Женгил","Караирим","Кара-Кеткен","Каракультас","Каракум","Кара-Кунгур","Карамурын","Каранозек","Караой","Кара-Сай","Карасор","Каратогай","Каратомар","Караузяк","Караулкельды","Карашокат","Кауданды","Кауылжыр","Кемер","Кенбай","Кендала","Кенжалы","Кенская","Керегетас","Керкелмес","Кзыл-Орда","Кзыл-Сай","Кзылтан","Кзыл-Ту","Кизил-Там","Киик","Киялы","Кияхты","Ковыльная","Койбагар","Койкрыккан","Кокдомбак","Кокпекты","Коксу","Коктас","Коктерек","Коктинколи","Кок-Тобе","Коктобе","Коктума","Кокшалгын","Кокшетау-1","Кокшетау-2","Колузаново","Колутон","Конту","Копа","Копан-Булак","Копмола","Копр","Коржункуль","Коркол","Кос-Арал","Коскудук","Костуин","Косчеку","Котыртас","Кошкентал","Кошкен-Тениз","Красивый Казахский","Крушдаласы","Кубек","Кудык","Кудыксай","Кулакшино","Кулан-Тобе","Кулжабасы","Кульсары","Кум-Тобе","Кумшагал","Куншагыр","Курагаты","Курайлы","Кургасын","Курдай","Курколь","Куркудук","Куркуреу-Су","Курлык","Курорт Боровое","Кустанай","Кушмурун","Кызгалдакты","Кызылджар","Кызылорда","Кызылсай","Кыргыз","Лепсы","Лисаковск","Луговая","Май","Майкайын","Майкудук","Майлибаш","Майлин","Майлытогай","Маймак","Май-Тобе","Макат","Макинка","Малай-Сары","Малдыбай","Мангышлак","Манкент","Мартук","Матай","Медеу","Мойынты","Монтай-Таш","Моюн-Кум","Мугалжар","Мукур","Мулалы","Мынадыр","Мын-Арал","Нарбота","Никельтау","Нилды","Новалы","Новоишимская","Ново-Усть-Каменогорск","Ногайты","Нура","О.П. 115 км","О.П. 116 км","О.П. 1347 км","О.П. 39 км","О.П. 42 км","О.П. 75 км","О.П. 80 км","О.П. 86 км","О.П.115 км","О.П.42 км","О.П.75 км","ОБ.П 42 км","Огневка","Октябрь","ОП 39 км","ОП 696 км","Опорная","Орта-Дересин","Осакаровка","Отар","П.П. 696 км.","Павлодар","Пепел","Первая Целинная","Перекатная","Петропавловск","ПЗ Аркарлы","ПЗ Батакара","ПЗ Тагалы","Пойма","Потуй","Пресногорьковская","Приишимская","Приречная","Притобольская","Разъезд 1","Разъезд 10","Разъезд 117","Разъезд 12","Разъезд 14","Разъезд 15","Разъезд 16","Разъезд 2","Чаган","Чаглинка","Чальдала","Чанак","Чашкан","Челгаши","Чемолган","Ченгельды","Черноводский","Чиганак","Чиили","Чильбастау","Чимкент","Чингирлау","Чкалово","Чокпак","Чокпар","Чольдала","Чумыш","Чурбай -Нура","Шагыр","Шалкар","Шар","Шаруа","Шетпе","Шешенкара","Шидерты","Шиликты","Шойтобе","Шокай","Шокысу","Шолаксай","Шолак-Су","Шолькызыл","Шоптыхак","Шорнак","Шортанды","Шубаркудык","Шынырау","Эгыз-Кызыл","Эспе","ЭЦ-3","Яик","Янко","Яны-Курган","Ирченко","Искине","Абаил","Адыр","Азат","Айгыржал","Айке","Айна-Булак","Айса","Айсары","Айтике Би","Акадыр","Акбалык","Ак-Булак","Ак-Булат","Акдала","Акеспе","Акжайдак","Аккемер","Аккудык","Ак-Куль","Аккум","Акмая","Аксенгир","Аксу-2","Аксуат","Актай","Актасты","Актогай","Ак-Узек","Ак-Чулак","Акшагыл","Акшокат","Акыр-Тюбе","Ала-Айгыр","Алабас","Алажиде","Алга","Алгабас","Алжан","Алтындала","Алтын-Дала","Алтынколат","Алтынсарин","Алтынтау","Амангельды","Аманкарагай","Анакуль","Анар","Ангаты","Анрахай","Апановка","Арал-Кум","Аральское море","Арганаты","Арка","Аркалык","Аркарлы","Арыстансор","Арысь-1","Арысь-2","Аспара","Атасу","Атбасар","Ащиголь","Ащису","Аягоз","Бабатай","Бадам","Базырово","Байгакум","Байсерке","Бай-Хожа","Байшешек","Бакай","Бактысай","Балгалы","Балхаш-1","Балхаш-2","Балыкты-Коль","Баранкульский","Батакара","Бауманская","Бахша","Бейнеу","Белкуль","Белоградовка","Бель","Береке","Берказан","Берлик-1","Берлик-2","Бесколь","Бестамак","Беш-Арык","Биже","Бикбаули","Биршогыр","Бозшаколь","Боктер","Боройнак","Босага","Бостандык","БП 7 км","Буйрек","Буркитты","Бурное","Буртескен","Бурубайтал","Бурундай","Бухтарма","Васильковка","Весна","Вишневка","Володарское","Воронинская","Гугня","Дала","Дарбаза","Дария","Даут","Дегелен","Дельбегетей","Денной","Державинская","Дермень-Тюбе","Джалагаш","Джалтыр","Джамбул","Джаныспай","Джемантуз","Джилга","Джусалы","Донгал","Дос","Доссор","Достык","Дюрмень-Тюбе","Егенсу","Едыге","Екибастуз-1","Екпенды","Ельтай","Енбекшиказах","Енбекшильдер","Енрекей","Ералиево","Еркеншилик","Ермаковка","Ерментау","Есиль","Жазык","Жаилма","Жайлау","Жайнак","Жайсан","Жайылма","Жаксы","Жаксыбулак","Жаксымай","Жаланашколь","Жалпак","Жаман-Ащи","Жамансор","Жамансу","Жана Караганды","Жанаарка","Жанажол","Жана-Семей","Жанатурмиз","Жангиз-Тобе","Жантерек","Жаркий","Жарлы","Жарма","Жарык","Жасказах","Жастар","Жасыл","Жезказган","Желдикара","Железорудная","Жем","Женис","Жетыбай","Жетыген","Жеты-Су","Жидели","Жилаево","Жингильды","Жинишке","Жирен-Айгыр","Жоламан","Жолкудук","Жоломан","Жомарт","Жоса","Жуантобе","Жуз-Агач","Жулдуз","Жумыскер","Журын","Жылан","Жыланды","Заводинка","Защита","Золоторунная","Зыряновск","Изимбет","Илецк-1","Илийская","Разъезд 265","Разъезд 236","Разъезд 279","Разъезд 312","Разъезд 313","Разъезд 315","Разъезд 377","Разъезд 460","Разъезд 461","Разъезд 463","Разъезд 464","Разъезд 465","Разъезд 467","Разъезд 468","Разъезд 469","Разъезд 47","Разъезд 470","Разъезд 472","Разъезд 6","Разъезд 6","Разъезд 102 км","Разъезд 117 км","Разъезд 12","Разъезд 12","Разъезд 15","Разъезд 16","Разъезд 17","Разъезд 19","Разъезд 2","Разъезд 20","Разъезд 21","Разъезд 22","Разъезд 25","Разъезд 27","Разъезд 3","Разъезд 30","Разъезд 33","Разъезд 33","Разъезд 366 км","Разъезд 37","Разъезд 38","Разъезд 4","Разъезд 402","Разъезд 414","Разъезд 441","Разъезд 47","Разъезд 47","Разъезд 496","Разъезд 5","Разъезд 5","Разъезд 53","Разъезд 6","Разъезд 60 км","Разъезд 60","Разъезд 69 км","Разъезд 7","Разъезд 8","Разъезд 9","Разъезд 9","Разъезд Акжар","Разъезд Акмая","Разъезд Алжан","Разъезд Анакуль","Разъезд Бактысай","Разъезд Боройнак","Разъезд Бостандык","Разъезд Екпенды","Разъезд Енбекшиказах","Разъезд Жайылма","Разъезд Майлитогай","Разъезд Урдазы","Разъезд102 км","Разъезд 36","Разъезд Керкелмес","Сагарчин","Сагыз","Саз","Сайкан","Сайлы","Сай-Утес","Саксаульская","Сапак","Сарыагаш","Сарыкум","Сарыкурак","Сары-Оба","Сары-Озек","Сарысай","Сары-Шаган","Сас-Тобе","Саумалколь","Сауран","Саяк","Селезневка","Селеты","Семипалатинск","Сергиевка","Серебрянка","Смирново","Соло-Тобе","Сороковая","Спутник","Сулусай","Сулы","Сурган","Сурум","Суук-Булак","Суурлы","Тавшон","Тагалы","Тагын-Кара","Таинча","Таксай","Талап","Талас","Талдыколь","Талды-Кудук","Талдынская","Талдысай","Тальщик","Тамды","Танкерис","Тансык","Тартугай","Тары","Тас","Таскескен","Таскудык","Тастак","Тасты-Талды","Татты","Тауарасы","Темекили","Темир","Темир-Булак","Тендык","Тениз","Теректы","Теренсай","Терень-Узяк","Тимекели","Тимур","Тобол","Тогыз","Токмансай","Токырау","Толтыр","Тосбулак","Тузово","Туйемойнак","Тургай","Тургусун","Туркестан","Тюлькубас","Тюмень-Арык","Тюратам","Увальненский","Уголки","Узень","Уинду","Уйтас","Уленты","Улпан","Уральск","Урдазы","Устюрт","Утрабат","Уч-Булак","Ушбийк","Ушкурай","Уш-Тобе","Уютный","Фурмановский","Хантау","Хорхут","Хромтау","Цвилинга","АБАЙ","МОСКВА","МОСКВА ПАВЕЛЕЦКАЯ","РЯЖСК 1","РЯЗАНЬ 2","ОЖЕРЕЛЬЕ","УЗУНОВО","ОСТ.ПУНКТ 33 КМ","ПАВЕЛЕЦ-ТУЛЬСКИЙ","РЯЗАНЬ","СМОЛЕНСК ЦЕНТРАЛЬНЫЙ","САСОВО","УЗЛОВАЯ 1","ЕФРЕМОВ","БЕКАСОВО-СОРТ.","ОСТ.ПУНКТ 23 КМ","ПОВАРОВО 3","ОСТ.ПУНКТ 202 КМ","ВЯЗЬМА","ЯРЦЕВО","ДУХОВСКАЯ","ШИЛОВО","КРАСНОЕ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 202 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 49 КМ","САНКТ-ПЕТЕРБУРГ","КРАСНОЕ","ПОВАРОВО 1","ВЫШНИЙ ВОЛОЧЕК","ОКУЛОВКА","МАЛАЯ ВИШЕРА","ТВЕРЬ","БОЛОГОЕ-МОСКОВСКОЕ","ТИХВИН","ВОЛХОВСТРОЙ 2","БАБАЕВО","ОСТ.ПУНКТ 23 КМ","РАЗ'ЕЗД N7","ОСТ.ПУНКТ 45 КМ","ЯРОСЛАВЛЬ","ЯРОСЛАВЛЬ","БУЙ","ВОРКУТА","КОТЛАС","ПАПРИХА","ВОХТОГА","ШАБАЛИНО","ПОНАЗЫРЕВО","ШАРЬЯ","ШЕКШЕМА","МАНТУРОВО","НЕЯ","НИКОЛО-ПОЛОМА","АНТРОПОВО","ЛОПАРЕВО","ГАЛИЧ","РОССОЛОВО","БРАНТОВКА","ЧЕРЕПОВЕЦ 2","ОСТ.ПУНКТ 45 КМ","ОСТ.ПУНКТ 202 КМ","ЛИПЕЦК","КАРЬЕРНАЯ","ВАЛУЙКИ","РАНЕНБУРГ","КИРСАНОВ","БОГОЯВЛЕНСК","МИЧУРИНСК-ВОРОН","МИЧУРИНСК-УРАЛЬСКИЙ","ДОБРИНКА","ЖЕРДЕВКА","БАЙГОРА","БОРИСОГЛЕБСК","ТОКАРЕВКА","ТЕРНОВКА","НАРОДНАЯ","ГРИБАНОВКА","ТАЛОВАЯ","ПОВОРИНО","ОСТРОГОЖСК","БОБРОВ","НОВОХОПЕРСК","ДУПЛЯТКА","ПОТЬМА","РТИЩЕВО 1","ЛЕТЯЖЕВКА","АРКАДАК","БАЛАШОВ-ПАССАЖИРСКИЙ","ОСТ.ПУНКТ 23 КМ","КРАСНОЕ","ТЕРНОВКА","КАРЬЕРНАЯ","ЕЛЕЦ","ПЛАТОНОВКА","ОСТ.ПУНКТ 49 КМ","ОСТ.ПУНКТ 45 КМ","СТРАХОВО","РАЗ'ЕЗД N4","ЛИНЕВО","ТАТИЩЕВО","КАЛИНИНО","АТКАРСК","СЕБРЯКОВО","ИЛОВЛЯ 1","АРЧЕДА","ВОЛЖСКИЙ","СУРОВИКИНО","ЖУТОВО","ОБЛИВСКАЯ","КОТЕЛЬНИКОВО","ЕРШОВ","ВЛАДИМИРОВКА","ОЗИНКИ","АЛТАТА","МОКРОУС","УРБАХ","КРАСНЫЙ КУТ","ПАЛЛАСОВКА","ВЕРХНИЙ БАСКУНЧАК","ХАРАБАЛИНСКАЯ","АШУЛУК","АКСАРАЙСКАЯ","АЛЕКСИКОВО","ФИЛОНОВО","ОСТ.ПУНКТ 45 КМ","САМАРА","ПОТЬМА","КОВЫЛКИНО","РУЗАЕВКА","ИНЗА","УФА","ЗВЕЗДА","КИНЕЛЬ","ПОХВИСТНЕВО","НОВООТРАДНАЯ","БУГУРУСЛАН","ПРИЮТОВО","РАЕВКА","АБДУЛИНО","АКСАКОВО","АША","МЕЛЕУЗ","КУМЕРТАУ","СТЕРЛИТАМАК","САЛАВАТ","ОСТ.ПУНКТ 713 КМ","ОСТ.ПУНКТ 45 КМ","ОСТ.ПУНКТ 33 КМ","КИСЕЛЕВСК ПАСС","АРТЫШТА 2","ТРУДАРМЕЙСКАЯ","ЧЕРКАСОВ КАМЕНЬ","РУЛИХА","НОВОКУЗНЕЦК","ТАЙГА","Новороссийск","ТАССАЙ","ПЕТУХОВО","ОСТ.ПУНКТ 45 КМ","ДРУЖИНИНО","КАМЕНСК-УРАЛЬСКИЙ","ПЕРМЬ 2","ОСТ.ПУНКТ 33 КМ","НОВОАЛЕКСЕЕВКА","ОСТ.ПУНКТ 202 КМ","ОСТ.ПУНКТ 202 КМ","ОСТ.ПУНКТ 33 КМ","ОСТ.ПУНКТ 23 КМ","НИКЕЛЬ","ОСТ.ПУНКТ 23 КМ","ПЛАТОНОВКА","ПЕРВОЕ МАЯ","РАЕВКА","АНТРОПОВО","ОРЕНБУРГ","САКМАРСКАЯ","БУЗУЛУК","НОВООРСК","АЙДЫРЛЯ","БАЙТУК","ГОГИНО","ТАМЕРЛАН","ДОНГУЗСКАЯ","НИЖНЕУВЕЛЬСКАЯ","ЕМАНЖЕЛИНСК","САРАКТАШ","БЕРДЯУШ","КУВАНДЫК","МЕДНОГОРСК","УСТЬ-КАТАВ","НИКЕЛЬ","ОРСК","ВЕРХНИЙ УФАЛЕЙ","БРЕДЫ","КАТАЙСК","ДАЛМАТОВО","КАРГАПОЛЬЕ","КОСОБРОДСК","ТРОИЦК","ВАРГАШИ","ЛЕБЯЖЬЯ-СИБИРСКАЯ","МАКУШИНО","ПЕТУХОВО","ЗЛАТОУСТ","СУЛЕЯ","ВЯЗОВАЯ","КРОПАЧЕВО","ОСТ.ПУНКТ 23 КМ","КЫШТЫМ","ШАДРИНСК","ОСТ.ПУНКТ 33 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 33 КМ","ОСТ.ПУНКТ 33 КМ","РАЗ'ЕЗД N5","ОСТ.ПУНКТ 45 КМ","НОВОСИБИРСК","НОВОСИБИРСК-ЮЖНЫЙ","АЛЕЙСКАЯ","МОСКАЛЕНКИ","МАРИАНОВКА","ОМСК-ПАССАЖИРСКИЙ","БЕРДСК","БИЙСК","ЗАРИНСКАЯ","СИБИРСКАЯ","СЕЯТЕЛЬ","НОВОБЛАГОВЕЩЕНКА","ЕВСИНО","ЛИНЕВО","БЕЗМЕНОВО","ЦАПЛИНО","АЛТАЙСКАЯ","ОВЧИННИКОВО","ГОРДЕЕВО","ЗАГАЙНОВО","РУБЦОВСК","БУЛАНИХА","ЗОНАЛЬНЫЙ","ПРИСЯГИНО","ШТАБКА","РЕБРИХА","КОРЧИНО","ОВЕЧКИНО","ЛЕНЬКИ","КУЛУНДА","ЧЕРЕПАНОВО","ТОПЧИХА","ПОСПЕЛИХА","ШИПУНОВО","УСТЬ-ТАЛЬМЕНСКАЯ","ИСИЛЬКУЛЬ","БОЛЬШАЯ РЕЧКА","ГИЛЕВКА","ИСКИТИМ","ОСТ.ПУНКТ 23 КМ","ИРКУТСК","ГОРЯЧИЙ КЛЮЧ","ОСТ.ПУНКТ 45 КМ","РАЗ'ЕЗД N5","РАЗ'ЕЗД N7","РАЗ'ЕЗД N9","СОСНОВЫЙ","БОЛЬШАЯ РЕЧКА","ОСТ.ПУНКТ 45 КМ","КАЛИНИНО","ОСТ.ПУНКТ 45 КМ","ВЯТСКИЕ ПОЛЯНЫ","МУРОМ 1","НАВАШИНО","АРЗАМАС 2","СЕРГАЧ","ШУМЕРЛЯ","КАЗАНЬ ПАСС","БАЛЕЗИНО","ГЛАЗОВ","АГРЫЗ","ЯНАУЛ","САРАПУЛ","ВЫСЕЛКИ","КАНАШ","ВЕКОВКА","ОСТ.ПУНКТ 23 КМ","НАРОДНАЯ","ХОСТА","ЛОО","ЛАЗАРЕВСКАЯ","ГОРЯЧИЙ КЛЮЧ","ВОДОПАДНЫЙ","САЛЬСК","СОЧИ","ДВОЙНАЯ","ТУАПСЕ-ПАСС","АДЛЕР","ДИНСКАЯ","ПЛАСТУНОВСКАЯ","ВЫСЕЛКИ","ТИХОРЕЦКАЯ","ПОРОШИНСКАЯ","ШУБАРКОЛЬ","Урумчи","Свердловск РЖД","Тараз","Экибастуз-1","ТОМСК 2","Анапа","КОТЕЛЬНИЧ 1","Жанатас","Каратау","Ковдор","Полтава Киевская","Шымкент","ОП Актау","Атырау","Саратов-1","Чу","Курган","ЧЕРЕПОВ 1","БАРНАУЛ","ЧЕРЕПОВ 1","АСТРАХАНЬ-1","Джетыгара","миасс 1","Прокопьевск Пасс","МИНСК-ПАССАЖИРСКИЙ","Пенза1","Пенза2","Пенза3","Пенза4","Пермь 2","Тамбов 1","ГРЯЗИ-ВОРОНЕЖСКИЕ","Белгород","Сызрань-1","Киров Пасс","Талды-Курган","КРАСНОДАР 1","Алексеевка","ЧЕЛЯБИНСК-ГЛАВНЫЙ","Волгоград 1","Локоть Гр.","Казань","Лиски","АСТАНА","АЛМА АТА 2","АЛМА АТА 1","КАРАГАНДЫ","КАРАГАНДЫ ПАСС","РОВНОЕ","Алтынколь","АКСУ-1","Карталы 1","Вологда 1","ЕКАТЕРИНБУРГ-П","КОРЕНОВСК","ЕЯ","БЕЛОГЛИНСКАЯ","ПЕСЧАНОКОПСКАЯ","РАЗВИЛЬНАЯ","РЕМОНТНАЯ","АРМАВИР","ЗИМОВНИКИ","ОСТ.ПУНКТ 33 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 33 КМ","ОСТ.ПУНКТ 23 КМ","СЕЛЕКЦИОННАЯ","ХУТУНЫ","ГУКОВО","БЕЛАЯ КАЛИТВА","ТАЦИНСКАЯ","ЛИХАЯ","МОРОЗОВСКАЯ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 45 КМ","ОСТ.ПУНКТ 45 КМ","АЧКАСОВО","РАЗ'ЕЗД N9","РАЗ'ЕЗД N11","ЗАБЫТЫЙ РЗД","КАЛИНИНО","СИМФЕРОПОЛЬ ПАСС","ДЖАНКОЙ","БАХЧИСАРАЙ","ТОЛОЧИН","БАРАНОВИЧИ-ЦЕНТРАЛЬНЫЕ","БРЕСТ-ЦЕНТРАЛЬНЫЙ","ОРАНЧИЦЫ","ЖАБИНКА","ЖОДИНО","ОРША-ЦЕНТРАЛЬНАЯ","БОРИСОВ","ЗВЕЗДА","БЕРЕЗА ГОРОД","ОЗЕРНАЯ","ИВАЦЕВИЧИ","КИЕВ","КОРОСТЕНЬ","УШИЦА","ШЕПЕТОВКА","ОСТ.ПУНКТ 45 КМ","ЛОЗОВАЯ","ХАРЬКОВ-ПАСС","ОСТ.ПУНКТ 23 КМ","КУПЯНСК УЗЛОВОЙ","ТОПОЛИ","ЛОЗОВАЯ","ЛЮБОТИН","РОМОДАН","ГРЕБЕНКА","ЛУБНЫ","МИРГОРОД","РЕШЕТИЛОВКА","ПОЛТАВА КИЕВСКАЯ","ОСТ.ПУНКТ 23 КМ","ПЛАТОНОВКА","ФЕДОРОВКА","СЕЛЕКЦИОННАЯ","КРАСНОЕ","ОСТ.ПУНКТ 49 КМ","ВЛАДИМИРОВКА","СИМФЕРОПОЛЬ","СИМФЕРОПОЛЬ ПАСС","НИЗЯНЫ","МЕЛИТОПОЛЬ","ВЕРХНЫЙ ТОКМАК 2","ДНЕПРОПЕТРОВСК ГЛАВНЫЙ","ДЖАНКОЙ","ПАВЛОГРАД 1","КАМЫШ-ЗАРЯ","НОВОАЛЕКСЕЕВКА","РАЗ'ЕЗД N5","БАХЧИСАРАЙ","НОВОМОСКОВСК ДНЕПРОВСК","ФЕДОРОВКА","ДОНЕЦК","ПЕРЕЛЕСКИ","ЯСИНОВАТАЯ ПАСС.","БАЙРАК","КРАСНАЯ МОГИЛА","ДЕБАЛЬЦЕВО","ВОЛНОВАХА","ГОРЛОВКА","МАКЕЕВКА ПАСС","ЗДОЛБУНОВ","КОВЕЛЬ","ГАЛИЧ","РОВНО","ОСТ.ПУНКТ 49 КМ","ЯГОДИН","РОВНОЕ","КИВЕРЦЫ","ОСТ.ПУНКТ 202 КМ","ОСТ.ПУНКТ 23 КМ","ОСТ.ПУНКТ 33 КМ","ПАРКОВЫЙ","СОСНОВЫЙ","ОП 57 КМ","ОБГ ПУНКТ N41","КОК-БЕКТЫ","КЗЫЛ-АСКЕР","АКЖИГИТ","УЗЫНКУДУК","КАЗИЕВКА","ОСТ.ПУНКТ 45 КМ","ОСТ.ПУНКТ 49 КМ","РЕМОВСКАЯ","НЕВЕРОВСКАЯ","МАСАЛЬСКАЯ","КАЗАХСТАНСКИЙ","УСТЬ-ТАЛОВКА","ФЕСТИВАЛЬНАЯ","ПРЕДГОРНАЯ","ИРТЫШСКИЙ ЗАВОД","ЧЕРЕМШАНКА","АУЛ","БЕЛЬ-АГАЧ","ДЮСАКЕН","ШОПТЫХАК","ДЕЛЬБЕГЕТЕЙ","РАЗ'ЕЗД N4","РАЗ'ЕЗД N7","РАЗ'ЕЗД N9","РАЗ'ЕЗД N5","КАРАТАС","ЕГИНСУ","ЛЕНИНОГОРСК","КАБУЛ-САЙ","АКТАС","КОРШУНОВО","ШЕМОНАИХА","КОКБУЛАК","ТИШИНСКАЯ","ТРЕТЬЯКОВО","АКТОБЕ","АККУДЫК","ГАНЮШКИНО","ИСАТАЙ","АК-КИСТАУ","МАХАМБЕТ","БЕКБЕКЕ","КАУЫЛЖЫР","СОЛЕНАЯ","КОК-БЕК","КОПОТАЙ","КАРА-КЕТКЕН","СЕМИГЛАВЫЙ МАР","ШИПОВО","ПЕРЕМЕТНАЯ","ЖАЙЫК","АФАНАСЬЕВО","БАКСАЙ","ТУМАННОЕ","РАЗ'ЕЗД 174 КМ","ОСТ.ПУНКТ 202 КМ","НАРЫН","САЗАНКУРАК","РАЗ'ЕЗД N11","РАЗ'ЕЗД N10","РАЗ'ЕЗД N9","РАЗ'ЕЗД N7","РАЗ'ЕЗД N5","РАЗ'ЕЗД N4","БУЙРЕКТОБЕ","КАРАГАЛИНСКАЯ","ДУДАКОВКА","КАРЬЕРНАЯ","РАЗ'ЕЗД N5","РАЗ'ЕЗД N9","ШАКАТ","СЕЛЕКЦИОННАЯ","РАЗ'ЕЗД N9","ОБГОННЫЙ ПУНКТ N75","ДЕНИСОВКА","ПЕРЕЛЕСКИ","САРЫКОЛЬ","КОСКУЛЬ","ЗОЛОТОРУННАЯ","ТАЙНЧА","ШЕПТЫКУЛЬ","ПЕРВОЕ МАЯ","ПАРОВОЗНОЕ ДЕПО","ОСТ.ПУНКТ 713 КМ","ЖАНААУЛ","АККУДЫК","СОЦГОРОДОК","АЛКАУ","АСТАХОВКА","АЩЫОЗЕК","ОЗЕРНАЯ","КОКДОМБАК","НУРА","ТАГАЙЛЫ","ЧУРБАЙ-НУРА","КРАСНОАРМЕЙКА-КАЗАХСК","МАРАЛДЫ","ШАРБАКТЫ","КУРКАМЫС","КАЕРАК","МЕДЕТ","КУНШАШ","БАСАГАШ","БАРМАК","ТОГУЗАК","ПЕШКОВСКИЙ ТУПИК","ДЖАРКУЛЬ","УСПЕНОВКА","ТЕМИРТАУ","ТАШКЕНТ","КУДУКЛИ ЭКСП","ТУРТГУЛ","МИСКИН","УЧКУДУК-2","АМУЗАНГ","КЫРККЫЗ","ТЕРМЕЗ","РАЗ'ЕЗД N10","ОСТ.ПУНКТ 49 КМ","КИЙИКСАЙ","АК ТОБЕ","БЕРДАХ","КУАНЫШ","САМАРКАНД","АЖИНИЯЗ","БАРСА-КЕЛЬМЕС","КЕЛЕС","ЖАСЛЫК","РАЗ'ЕЗД N9","КАРШИ","БЕКАБАД","БОГАРНОЕ","МАРОКАНД","УРГЕНЧ","КАРАКАТТА","АКЖИГИТ","КАРАКАЛПАКИЯ","БОСТАН","АБАДАН","КУНХОДЖА","РАУШАН","КУНГРАД","НАВОИ","НУКУС","НАЗАРХАН","УРУМЧИ","ХЕЛМ","ЛЮБЛИН","ПУЛАВИ МЯСТО","ВАРШАВА ЦЕНТРАЛЬНАЯ","ВАРШАВА ВСХОДНЯ","ВАРШАВА ЗАХОДНЯ","ДОРОХУСК","ВАРШАВА","ЕРЕВАН","БИШКЕК","МЕРКЕ","КАИНДЫ","БЕЛОВОДСКАЯ","ШОПОКОВО","БИШКЕК 1","БЫСТРОВКА","РЫБАЧЬЕ","КАРАБАЛТА","ДУШАНБЕ","ШААРТУЗ","КУРГАН-ТЮБЕ","ДАНГАРА","КУЛЯБ","ХОШАДЫ","НАУ","ХУДЖАНД","ТАЛИМАРДЖАН","ТОМСК 1","ЗААЯТСКАЯ","МЫРЗА","Карабулак","БАТАЛЫ","АКСУ","БОЛАШАК","КИРГИЛЬДЫ","БАРАНОВИЧИ","Усть-Каменогорск"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    String way = "";
    Boolean isIncreasing = true;
    int size = 0;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fromStation = (EditText) findViewById(R.id.fromStation);
        toStation = (EditText) findViewById(R.id.toStation);
        date = (EditText) findViewById(R.id.date);
        search_btn = (Button) findViewById(R.id.search);

        fromStation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    way = "from";
                    Log.d("focus", "focus:: from");
                }
            }
        });

        toStation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    way = "to";
                    Log.d("focus", "focus:: to");
                }
            }
        });

        fromStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!fromStation.getText().toString().matches("") && way.equals("from")) {
//                    kilograms = Float.valueOf(eText.getText().toString());
//                    pounds = (float) (kilograms * (2.20462));
//                    eText2.setText(Float.toString(pounds));
                    Log.d("focus", "<::from station changed");
                }
            }
        });

        toStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!toStation.getText().toString().matches("") && way.equals("to")) {
//                    kilograms = Float.valueOf(eText.getText().toString());
//                    pounds = (float) (kilograms * (2.20462));
//                    eText2.setText(Float.toString(pounds));
                    Log.d("focus", "::>to station changed");
                }
            }
        });

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(size > date.getText().length()){
                    isIncreasing = false;
                }
                else if(size < date.getText().length()){
                    isIncreasing = true;
                }
                if((date.getText().toString().length() == 2 && isIncreasing) || (date.getText().toString().length() == 5 && isIncreasing)) {
                    date.setText(date.getText() + ".");
                    date.setSelection(date.getText().length());
                }
                size = date.getText().length();
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromSt = fromStation.getText().toString();
                String toSt = toStation.getText().toString();
                String dt = date.getText().toString();
                //Log.d("res", "from -> " + fromSt + ":: to -> " + toSt + ":: date -> " + dt);
                Intent intent;
                intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("from_station", fromSt);
                intent.putExtra("to_station", toSt);
                intent.putExtra("date", dt);
                startActivity(intent);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
