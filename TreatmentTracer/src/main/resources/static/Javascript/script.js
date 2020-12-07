$(document).ready(function(){

    $("#form1").css({"height":$(window).height()+ "px"});
    $("#form2").css({"height":$(window).height()+ "px"});
    $(".indexbody").css({"height":$(window).height()+ "px"});
    
    if($(".body").height()<= $(window).height()){
    	 $(".body").css({"height":$(window).height()+ "px"});
    }
    if($(".routine").height()<= $(window).height()){
   	 $(".routine").css({"height":$(window).height()+ "px"});
   }

    if($(".measurements").height()<= $(window).height()){
    	$(".measurements").css({"height":$(window).height()+ "px"});
    }
    
    if($(".appointment").height()<= $(window).height()){
    	$(".appointment").css({"height":$(window).height()+ "px"});
    }
    if($(".proximalDate").height()<= $(window).height()){
    	$(".proximalDate").css({"height":$(".calendarjs").height()+ "px"});
    }
    if($(".addDate").height()<= $(window).height()){
    	$(".addDate").css({"height":$(".calendarjs").height()+ "px"});
    }
    
    if($(".diseases").height()<= $(window).height()){
    	$(".diseases").css({"height":$(window).height()+ "px"});
    }
    
    if($(".patients").height()<= $(window).height()){
    	$(".patients").css({"height":$(window).height()+ "px"});
    }
    if($(".carers").height()<= $(window).height()){
    	$(".carers").css({"height":$(window).height()+ "px"});
    }
    

});