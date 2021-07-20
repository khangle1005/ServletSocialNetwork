<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messages</title>
<link href="./css/messages.css?v=1" rel="stylesheet" type="text/css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">

    <!-- Page header start -->
    <div class="page-title">
        <div class="row gutters">
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                <h5 class="title">Messages</h5>
            </div>
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12"> </div>
        </div>
    </div>
    <!-- Page header end -->

    <!-- Content wrapper start -->
    <div class="content-wrapper">

        <!-- Row start -->
        <div class="row gutters">

            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">

                <div class="card m-0">

                    <!-- Row start -->
                    <div class="row no-gutters">
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-3 col-3">
                            <div class="users-container">
                                <div class="chat-search-box">
                                    <div class="input-group">
                                        <input class="form-control" placeholder="Search">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-info">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <ul class="users">
                                    
                                </ul>
                            </div>
                        </div>
                        <div class="col-xl-8 col-lg-8 col-md-8 col-sm-9 col-9">
                            <div class="selected-user">
                                <span>To: <span class="name"></span></span>
                            </div>
                            <div class="chat-container">
                                <ul class="chat-box chatContainerScroll">
	                                
                                </ul>
                                <div class="form-group mt-3 mb-0">
                                    <textarea id="chat-input" class="form-control" rows="3" placeholder="Type your message here..."></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Row end -->
                </div>

            </div>

        </div>
        <!-- Row end -->

    </div>
    <!-- Content wrapper end -->

</div>

<script>
$(document).ready(function(){
	$.ajax({
	    	type: 'GET',
	        url: 'LoadFriendsControl',
	        data: '',
	        error: function(response) {
	        },
	        success: function(response) {
	        	$(".users").html(response);
	        }
	});
});

$(document).on('click', '.person', function () {
	$(this).parent().find(".active-user").removeClass("active-user");
	$(this).addClass("active-user");
	
	var name = $(this).find(".name-time").find(".name").text();
	$(".selected-user").children().find(".name").text(name);
	
	var user_id = $(this).attr("id");
	$("#chat-input").attr("to_user", user_id);
	
	$.ajax({
    	type: 'GET',
        url: 'LoadMessagesControl',
        data: 'user_id=' + user_id,
        error: function(response) {
        },
        success: function(response) {
        	$(".chat-box").html(response);
        }
	});
});

$("#chat-input").on("keydown", function(e){
		
	  if(e.which == 13){
		  
	    var user_id = $(this).attr("to_user");
		var text = $(this).val();

	    $.ajax({
	    	type: 'GET',
	        url: 'SendMessageControl',
	        data: 'user_id2=' + user_id + '&text=' +text,
	        error: function(response) {
	        },
	        success: function(response) {
	        	
	        }
		});
	    
	    $(this).val("");
	    return false;
	  }
});

//cap nhat tin nhan moi 1s
function loadNewMessages(){
	var user_id = $(".active-user").attr("id");
	
	$.ajax({
    	type: 'GET',
        url: 'LoadMessagesControl',
        data: 'user_id=' + user_id,
        error: function(response) {
        },
        success: function(response) {
        	$(".chat-box").html(response);
        }
	});
}
window.setInterval(loadNewMessages, 1000*5)
</script>
</body>
</html>