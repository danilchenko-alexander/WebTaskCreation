function insertDate() {
    var x = document.getElementById("period").value;
    var date = new Date();
    var currentYear = date.getFullYear();
    var currentMonth = date.getMonth();

    switch (x){
        case 'Last Quarter':{
            var month = date.getMonth()+1;
            var quarter = (Math.ceil(month / 3));
            var startQuarterMonth = quarter*3-2;
            var endQuarterMonth = quarter*3;
            var lastDayOfQuarterMonth = getLastDayOfMonth(currentYear, endQuarterMonth);

            document.getElementById('sd').value = currentYear+'-'+startQuarterMonth+'-'+'01';
            document.getElementById('ed').value = currentYear+'-'+endQuarterMonth+'-'+lastDayOfQuarterMonth;
        }break;
        case 'Last Month':{
            var lastDayOfMonth = getLastDayOfMonth(currentYear,currentMonth);

            document.getElementById('sd').value = currentYear+'-'+(currentMonth+1)+'-'+'01';
            document.getElementById('ed').value = currentYear+'-'+(currentMonth+1)+'-'+lastDayOfMonth;
        }break;
        case 'Last Week':{
            var startWeekDate = getStartWeekDate(date);//
            var startWeekMonth = startWeekDate.getMonth();
            var startWeekYear = startWeekDate.getFullYear();
            var startWeekDay = formatDay(startWeekDate.getDate());

            var endWeekDate = getEndWeekDate(date);
            var endWeekMonth = endWeekDate.getMonth();
            var endWeekYear = endWeekDate.getFullYear();
            var endWeekDay = formatDay(endWeekDate.getDate());

            document.getElementById('sd').value = startWeekYear+'-'+(startWeekMonth+1)+'-'+startWeekDay;
            document.getElementById('ed').value = endWeekYear+'-'+(endWeekMonth+1)+'-'+endWeekDay;
        }break;
        case 'Current Quarter to Date':{
            var month = date.getMonth()+1;
            var quarter = (Math.ceil(month / 3));
            var startQuarterMonth = quarter*3-2;
            var day = formatDay(date.getDate());

            document.getElementById('sd').value = currentYear+'-'+startQuarterMonth+'-'+'01';
            document.getElementById('ed').value = currentYear+'-'+(currentMonth+1)+'-'+day;
        }break;
        case 'Current Month to Date':{
            var day = formatDay(date.getDate());

            document.getElementById('sd').value = currentYear+'-'+(currentMonth+1)+'-'+'01';
            document.getElementById('ed').value = currentYear+'-'+(currentMonth+1)+'-'+day;
        }break;
        case 'Current Week to Date':{
            var startWeekDate = getStartWeekDate(date);//
            var startWeekMonth = startWeekDate.getMonth();
            var startWeekYear = startWeekDate.getFullYear();
            var startWeekDay = startWeekDate.getDate();

            document.getElementById('sd').value = startWeekYear+'-'+(startWeekMonth+1)+'-'+startWeekDay;
            document.getElementById('ed').value = currentYear+'-'+(currentMonth+1)+'-'+formatDay(date.getDate());
        }break;
        default:{
            document.getElementById('sd').value = '';
            document.getElementById('ed').value = '';
        }
    }
}

//Преобразует число в правильный формат данных для записи в базу
function formatDay(day) {
    return (day < 10? '0'+day:day);
}

//Возвращает последний день месяца
function getLastDayOfMonth(Year, Month) {
    return new Date((new Date(Year,Month+1,1))-1).getDate();
}

//Возвращает дану начала недели
function getStartWeekDate(date) {
    var day = date.getDay();//17
    var diff = date.getDate()-day+(day == 0 ? -6:1);//11
    return new Date(new Date()-(date.getDate()-diff)*24*60*60*1000);
}

//Возвращает дату конца недели
function getEndWeekDate(date) {
    var day = date.getDay();
    var diff = date.getDate()+6-day+(day == 0 ? -6:0);
    return new Date(new Date()+(diff - date.getDate())*24*60*60*1000);
}