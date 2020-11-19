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
            document.getElementById("botonModal").value=calEvent.id;
            
        },  
	});
    
    var title = document.getElementsByClassName("titleApp");
    var date = document.getElementsByClassName("dateApp");
    var annot = document.getElementsByClassName("annotApp");
    var espec = document.getElementsByClassName("espApp");
    var ids = document.getElementsByClassName("IdApp");
    
    var events = new Array();
    for (var i=0; i<title.length; i++) { 
    	
    	var newEvent = new Object();
    	newEvent.id= ids[i].innerText;
        newEvent.title = title[i].innerText;
        newEvent.start = date[i].innerText
        newEvent.description ="<b>Especialidad: </b>" +  espec[i].innerText + "<br><b>Anotaciones: </b>" + annot[i].innerText;
        events.push(newEvent);
        
        	
        
    	
    }
    
    $('#calendar').fullCalendar( 'renderEvents', events );
    
    
    $(".fc-prev-button").click(function () {
        
    	 $('#calendar').fullCalendar( 'renderEvents', events );
      });
    
    $(".fc-next-button").click(function () {
        
   	 $('#calendar').fullCalendar( 'renderEvents', events );
     });
    
    $(".fc-today-button").click(function () {
        
      	 $('#calendar').fullCalendar( 'renderEvents', events );
        });
    
});