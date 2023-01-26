<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="ru">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Сети 2022, #3: Места</title>
	<link rel="stylesheet" type="text/css" href="tasks.css">
</head>
<body>
<div class="task">
<a id="toList" href="list.html">[к списку]</a>
<h1>Места (Асинхронное сетевое взаимодействие)</h1>
<p>
Используя методы асинхронного программирования (например, CompletableFuture для Java) или библиотеки реактивного программирования (RxJava, например) провзаимодействовать с несколькими публично доступными API и сделать приложение с любым интерфейсом, основанное на этих API.
При этом API должны использоваться так:
<ul>
<li>Все вызовы должны делаться с помощью HTTP-библиотеки с асинхронных интерфейсом;</li>
<li>Все независимые друг от друга вызовы API должны работать одновременно;</li>
<li>Вызовы API, которые зависят от данных, полученных из предыдущих API, должны оформляться в виде асинхронной цепочки вызовов;</li>
<li>Все результаты работы должны собираться в общий объект, поэтапный вывод результатов в процессе работы недопустим;</li>
<li>Не допускаются блокировки на ожидании промежуточных результатов в цепочке вызовов, допустима только блокировка на ожидании конечного результата (в случае консольного приложения).</li>
<li>Другими словами, логика программы должна быть оформлена как две функции, каждая из которых возвращает CompletableFuture (или аналог в вашем ЯП) без блокировок. Первая функция выполняет п.2, а вторая &mdash; п.п. 4 и 5 из списка ниже.</li>
</ul>
<p>
Логика работы:
<ol>
<li>В поле ввода пользователь вводит название чего-то (например "Цветной проезд") и нажимает кнопку поиска;</li>
<li>Ищутся варианты локаций с помощью метода [1] и показываются пользователю в виде списка;</li>
<li>Пользователь выбирает одну локацию;</li>
<li>С помощью метода [2] ищется погода в локации;</li>
<li>С помощью метода [3] ищутся интересные места в локации, далее для каждого найденного места с помощью метода [4] ищутся описания;</li>
<li>Всё найденное показывается пользователю.</li>
</ol>
<p>
Методы API:
<ul>
<li style="list-style-type: '[1]&nbsp;'">получение локаций с координатами и названиями: <a href="https://docs.graphhopper.com/#operation/getGeocode">https://docs.graphhopper.com/#operation/getGeocode</a></li>
<li style="list-style-type: '[2]&nbsp;'">получение погоды по координатам <a href="https://openweathermap.org/current">https://openweathermap.org/current</a></li>
<li style="list-style-type: '[3]&nbsp;'">получение списка интересных мест по координатам: <a href="https://opentripmap.io/docs#/Objects%20list/getListOfPlacesByRadius">https://opentripmap.io/docs#/Objects%20list/getListOfPlacesByRadius</a></li>
<li style="list-style-type: '[4]&nbsp;'">получение описания места по его id: <a href="https://opentripmap.io/docs#/Object%20properties/getPlaceByXid">https://opentripmap.io/docs#/Object%20properties/getPlaceByXid</a></li>
</ul>
<p>
Баллов за задачу: 3.<br>
Дедлайн для групп Ипполитова: 16.10.2022
</p>
</div>
</body>
</html>
