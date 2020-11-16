function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

var hoy = new Date();
var dd = hoy.getDate();
if(dd<10) {
    dd='0'+dd;
} 
 
if(mm<10) {
    mm='0'+mm;
}

var mm = hoy.getMonth()+1;
var yyyy = hoy.getFullYear();

dd=addZero(dd);
mm=addZero(mm);
$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next',
            center: 'title'
        },
        defaultDate: yyyy+'-'+mm+'-'+dd,
        buttonIcons: true, // show the prev/next text
        weekNumbers: false,
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        eventClick: function (calEvent, jsEvent, view) {
            $('#event-title').text(calEvent.title);
            $('#event-description').html(calEvent.description);
            $('#modal-event').modal();
        },  
	});
    
    var title = document.getElementsByClassName("titleApp");
    var date = document.getElementsByClassName("dateApp");
    var annot = document.getElementsByClassName("annotApp");
    var espec = document.getElementsByClassName("espApp");
    for (var i=0; i<title.length; i++) { 
    	
    	var newEvent = new Object();
    	
        newEvent.title = title[i].innerText;
        newEvent.start = date[i].innerText
        newEvent.description ="<b>Especialidad: </b>" +  espec[i].innerText + "<br><b>Anotaciones: </b>" + annot[i].innerText;
        
        $('#calendar').fullCalendar( 'renderEvent', newEvent );
    	
    }
    
});