PrimeFaces.locales['ru'] = {
    closeText: 'OK',
    prevText: 'Назад',
    nextText: 'Вперед',
    monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь','Ноябрь','Декабрь'],
    monthNamesShort: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь','Ноябрь','Декабрь'],
    dayNames: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Субота'],
    dayNamesShort: ['Воск', 'Пон', 'Вт', 'Ср', 'Четв', 'Пят', 'Суб'],
    dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
    weekHeader: 'Неделя',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix:'',
    timeOnlyTitle: 'Только время',
    timeText: 'Время',
    hourText: 'Час',
    minuteText: 'минута',
    secondText: 'секунда',
    currentText: 'Текущее',
    ampm: false,
    month: 'месяц',
    week: 'неделя',
    day: 'день',
    allDayText: 'Весь день'
};

function sbmt(objId) {
    PrimeFaces.ab({source:objId});
}

function barChartExt() {
    this.cfg.grid = { background: 'white' };
    this.cfg.legend = { show: true, location: 'ne' };
    this.cfg.highlighter = { tooltipLocation: 'e', tooltipAxes: 'x' };
}

function pieChartExt() {
    this.cfg.grid = { background: 'white' };
    this.cfg.legend = { show: false};
}

function lineChartExt() {
    this.cfg.axes.xaxis.showTicks = false;
    this.cfg.grid = { background: 'white' };
    this.cfg.legend = { show: true, location: 'nw' };
    this.cfg.highlighter = {
        tooltipLocation: 'n', 
        tooltipOffset: 10,
        tooltipContentEditor: function(str, seriesIndex, pointIndex, plot) {
            return plot.options.axes.xaxis.ticks[pointIndex] + ": " + plot.data[seriesIndex][pointIndex];
        }
    };
}
