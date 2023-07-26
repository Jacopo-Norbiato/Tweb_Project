<%--
  Created by IntelliJ IDEA.
  User: 39345
  Date: 10/07/2023
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <meta charset="UTF-8">
    <title>Calendar</title>
    <meta name="viewport" content="width=devide-width, initial-scale 1.0">
    <link rel="stylesheet" href="calendar.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body onload="setDate()">
<header>
    <h1>Your Calendar</h1>
    <h5>Scorri per visualizzare i tuoi eventi </h5>
</header>
    <div class="container">
    <div class="calendar-container">
        <header class="calendar-header">
            <p class="calendar-current-date"></p>
            <div class="calendar-navigation">
                <span id="calendar-prev" class="fa fa-angle-left"></span>
                <span id ="calendar-next" class="fa fa-angle-right"></span>
            </div>
        </header>
        <div class="calendar-body">
            <ul class="calendar-weekdays">
            <li>Lun</li>
            <li>Mar</li>
            <li>Mer</li>
            <li>Gio</li>
            <li>Ven</li>
            <li>Sab</li>
            <li>Dom</li>
            </ul>
            <ul class="calendar-dates"></ul>
        </div>
    </div>
    <aside class="event" id="sidebar">

        <div class="event-title">
            <h3>Events of the Day</h3>
        </div>
        <div class="event">
            <h4>Evento 1</h4>
            <p>Dentista alle 10</p>
            <p>Ore 5-6</p>
            <div class="operations">
                <input type="button" class="modifica" value="Modifica"/>
            </div>
            </div>
    </aside>
    </div>

<script type="text/javascript">

    function setDate() {
        let date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth();

        const day = document.querySelector(".calendar-dates");
        const currdate = document.querySelector(".calendar-current-date");
        const prenexIcons = document.querySelectorAll(".calendar-navigation span");

        // Array of month names
        const months = [
            "Gennaio",
            "Febbraio",
            "Marzo",
            "Aprile",
            "Maggio",
            "Giugno",
            "Luglio",
            "Agosto",
            "Settembre",
            "Ottobre",
            "Novembre",
            "Dicembre"
        ];

        // Function to generate the calendar
        const manipulate = () => {

            // Get the first day of the month
            const dayone = new Date(year, month, 1).getDay();

            // Get the last date of the month
            const lastdate = new Date(year, month + 1, 0).getDate();

            // Get the day of the last date of the month
            const dayend = new Date(year, month, lastdate).getDay();

            // Get the last date of the previous month
            const monthlastdate = new Date(year, month, 0).getDate();

            // Variable to store the generated calendar HTML
            let lit = "";

            // Loop to add the last dates of the previous month
            for (let i = dayone; i > 0; i--) {
                lit += `<li class="inactive">${monthlastdate - i + 1}</li>`;
            }

            // Loop to add the dates of the current month
            for (let i = 1; i <= lastdate; i++) {

                // Check if the current date is today
                let isToday = i === date.getDate()
                && month === new Date().getMonth()
                && year === new Date().getFullYear()
                    ? "active"
                    : "";
                lit += `<li class="${isToday}">${i}</li>`;
            }

            // Loop to add the first dates of the next month
            for (let i = dayend; i < 6; i++) {
                lit += `<li class="inactive">${i - dayend + 1}</li>`
            }

            // Update the text of the current date element
            // with the formatted current month and year
            currdate.innerText = `${months[month]} ${year}`;

            // update the HTML of the dates element
            // with the generated calendar
            day.innerHTML = lit;
        }

        manipulate();

        // Attach a click event listener to each icon
        prenexIcons.forEach(icon => {

            // When an icon is clicked
            icon.addEventListener("click", () => {

                // Check if the icon is "calendar-prev"
                // or "calendar-next"
                month = icon.id === "calendar-prev" ? month - 1 : month + 1;

                // Check if the month is out of range
                if (month < 0 || month > 11) {
                    // Set the date to the first day of the
                    // month with the new year
                    date = new Date(year, month, new Date().getDate());

                    // Set the year to the new year
                    year = date.getFullYear();

                   // Set the month to the new month
                    month = date.getMonth();
                } else {
                    // Set the date to the current date
                    date = new Date();
                }
                // Call the manipulate function to
                // update the calendar display
                manipulate();
            });
        });
        }
</script>
</body>
</html>