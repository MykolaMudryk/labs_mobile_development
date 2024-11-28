package com.example.lab3.data

object ItemsData {
    val itemList = listOf(
        Item(
            1,
            "Замок Паланок",
            "Замок Паланок у Мукачеві є однією з найвідоміших історичних пам'яток Західної України. Цей середньовічний замок, розташований на горі, був заснований ще у XIV столітті. Замок використовувався як фортеця, резиденція магнатів, а згодом — як політична в'язниця. Нині це музей, що відкриває відвідувачам історію регіону та приголомшливі краєвиди з його стін.",
            "zamok_palanok"
        ),
        Item(
            2,
            "Олеський замок",
            "Один із найстаріших замків Львівщини, Олеський замок був побудований у XIV столітті. Замок знаменитий тим, що тут народився польський король Ян III Собеський. Замок відомий своїм музеєм, де зібрані унікальні твори мистецтва, а також своїм розташуванням на пагорбі, з якого відкривається краєвид на околиці.",
            "olesky_castle"
        ),
        Item(
            3,
            "Кам'янець-Подільська фортеця",
            "Ця фортеця вважається однією з найбільш мальовничих в Україні. Вона була побудована в XII-XIV століттях на високому березі річки Смотрич. Кам'янець-Подільська фортеця є історичною перлиною, яка відображає поєднання культур різних народів, що жили тут у різні епохи.",
            "kamianets_fortress"
        ),
        Item(
            4,
            "Скелі Довбуша",
            "Скелі Довбуша — це унікальний природний комплекс в Івано-Франківській області. Ці кам'яні утворення височіють посеред Карпатського лісу і оточені легендами про Олексу Довбуша, карпатського Робіна Гуда. Вони ідеально підходять для піших прогулянок, скелелазіння та насолоди природою.",
            "dovbush_rocks"
        ),
        Item(
            5,
            "Говерла",
            "Найвища точка України, гора Говерла, розташована в Карпатах на межі Івано-Франківської та Закарпатської областей. Висота гори становить 2061 метр, і вона є популярним місцем для туристів, які прагнуть підкорити вершину та насолодитися краєвидами.",
            "hoverla"
        ),
        Item(
            6,
            "Свірзький замок",
            "Цей замок є однією з менш відомих, але дуже мальовничих пам'яток Львівщини. Розташований поблизу села Свірж, замок був побудований ще у XV столітті. Його унікальність полягає у поєднанні оборонних споруд з природним ландшафтом, а також у романтичній атмосфері, яка приваблює багатьох митців та туристів.",
            "svirzh_castle"
        )
    )
}

class Item(val id: Int, val title: String, val description: String, val image: String)
