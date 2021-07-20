<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="./css/main.css?v=1" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container mt-3 mb-5">
    <div class="d-flex justify-content-center row">
    	<div id="left-sidebar" class="sidebar col-md-2">
    		<div class="side-menu row justify-content-start align-items-center"><i class="fa fa-home fa-2x" style="float:left;"></i><a href="Home"><h5 class="ml-2 mt-2">Home</h5></a></div>
    		<div class="side-menu row justify-content-start align-items-center"><i class="fa fa-user fa-2x"></i><a href="Profile?user_id=${sessionScope.user_id}"><h5 class="ml-2 mt-2">Profile</h5></a></div>
    		<div class="side-menu row justify-content-start align-items-center"><i class="fa fa-comments fa-2x"></i><a href="Messages"><h5 class="ml-2 mt-2">Messages</h5></a></div>
    		<div class="side-menu row justify-content-start align-items-center"><i class="fa fa-sign-out fa-2x"></i><a href="Logout"><h5 class="ml-2 mt-2">Logout</h5></a></div>
    	</div>
        <div class="col-md-8">
            <div class="feed">
                <div class="share border bg-white">
                <form action="PostControl" method="post" enctype="multipart/form-data">
                	<input type="text" class="inputtext" id="user_id" name="user_id"  value="${sessionScope.user_id}" style="display : none">
                    <div class="d-flex flex-row inputs p-2 py-4"><img class="rounded-circle" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg" width="40"><input type="text" id="post_content" name="post_content" class="border-0 form-control share-input" placeholder="Share your thoughts"></div>
                    <div class="d-flex flex-row justify-content-between border-top">
                        <div class="d-flex flex-row publish-options">
                        	<div class="align-items-center border p-2 share" style="border-radius: 40px; margin: 10px;">
                        		<i class="fa fa-picture-o"></i><span class="ml-1">Photo</span>
                        		<input type="file" name="file" accept="image/png, image/jpg, image/jpeg"/>
                        	</div>
                            <!-- <div class="align-items-center border-right p-2 share"><i class="fa fa-camera text-black-50"></i><span class="ml-1">Photo</span></div> -->
                            <!-- <div class="align-items-center border-right p-2 share"><i class="fa fa-video-camera text-black-50"></i><span class="ml-1">Video</span></div> -->
                            <!-- <div class="align-items-center border-right p-2 share"><i class="fa fa-file text-black-50"></i><span class="ml-1">Files</span></div> -->
                        </div>
                        <div class="publish-button">
                        	<input class="align-items-center border-left p-2 px-5 btn publish" type="submit" value="Post"/>
                        </div>
                    </div>
                </form>
                </div>
                <div class="ads mt-2">
                    <div class="p-2 bg-white">
                        <div class="d-flex flex-row justify-content-between align-items-center">
                            <h6>Senior backend developer</h6><span class="border px-2 ads-text">Ad</span>
                        </div>
                        <div class="ads-content"><span>We selected the best tech projects to find you the ideal jobs. we offers salaries from $70K -100K so what you are waiting for just apply now with your latest resume.</span></div>
                    </div>
                </div>
                
                <div id="feeds" class="feeds">
               <c:forEach items="${posts}" var="post">
	                <div class="feed-post mt-2" post_id="${post.get(0)}">
	                    <div class="p-2 bg-white">
	                        <div class="d-flex flex-row justify-content-between align-items-start profile">
	                            <div class="d-flex align-items-center"><img class="rounded-circle img-responsive" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg" width="50" height="50">
	                                <div class="d-flex flex-column ml-2">
	                                    <h6><a href="ViewUser?user_id=${post.get(1)}">${post.get(6)}</a></h6><span class="time">${post.get(2)}</span>
	                                </div>
	                            </div><i class="fa fa-ellipsis-h text-black-50 mt-2"></i>
	                        </div>
	                        <div class="profile-content"><span>${post.get(4)}</span>
	                            <div class="content-image"><img src="${pageContext.request.contextPath}${post.get(5)}"></div>
	                        </div>	                        
	                        <div class="profile-engagements">
	                        	<button post_id="${post.get(0)}" class="like-btn" style="border: none;"><i class="fa fa-thumbs-o-up"></i><span class="badge badge-light">${post.get(3)}</span></button>
	                        	<button style="border: none;"><i class="fa fa-comment-o"></i><span class="badge badge-light">${post.get(7)}</span></button>
	                        	<button style="float:right;border: none;"><i class="fa fa-share"></i></button>
	                        </div>
	                        
	                        <div class="show-comment border-bottom">
	                        	<a href="javascript:;" class="show-cmt-btn">Show comments</a>
<!-- 	                        	<div style="margin-top:10px" class="d-flex align-items-center"><img class="rounded-circle img-responsive" src="https://i.imgur.com/44HzzUN.jpg" width="40" height="40">
	                                <div class="d-flex flex-column ml-2">
	                                    <h6>username</h6>
	                                    ay la binh luan
	                                </div>
	                            </div>
	                            <div style="margin-top:10px" class="d-flex align-items-center"><img class="rounded-circle img-responsive" src="https://i.imgur.com/44HzzUN.jpg" width="40" height="40"><div class="d-flex flex-column ml-2"><h6>username</h6>day la binh luan</div></div> -->
	                        </div>
	                        
	                        <div class="post-comment">
							    <div>
								    <form class="send-comment" action="Comment" method="post">
								        <div class="input-group">
									        	<input type="text" name="post_id" value="${post.get(0)}" style="display : none">
									        	<input type="text" name="user_id" value="${sessionScope.user_id}" style="display : none">
									            <input id="post_comment" name="post_comment" type="text" class="form-control border-0 form-control share-input" placeholder="Type your comments" username="${sessionScope.username}"  />
									            <input id="send-commnent-btn" class="input-group-addon" type="submit" value="Send" style="background-color: Transparent; border: none;">
								        </div>
								    </form>
							    </div>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
                </div>
                
                <div class="ads mt-2">
                    <div class="p-2 bg-white">
                        <div class="d-flex flex-row justify-content-between align-items-center">
                            <h6 class="mt-1">Adobe photoshop XD</h6><span class="border px-2 ads-text">Sponsored</span>
                        </div>
                        <div class="ads-content"><span>We selected the best tech projects to find you the ideal jobs. we offers salaries from $70K -100K so what you are waiting for just apply now with your latest resume.</span><img class="img-fluid img-responsive mt-2" src="https://i.imgur.com/C1U3zy4.jpg"></div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="right-sidebar" class="sidebar col-md-2">
	        <div id="search-bar" class="form-outline mb-2">
  				<input type="search" id="search-input" class="form-control" placeholder="Search for users" aria-label="Search" />
			</div>
			<div id="search-results" class="border mb-2">
			</div>
			<div class="ads mb-2">
                    <div class="p-2 bg-white">
                        <div class="d-flex flex-row justify-content-between align-items-center">
                            <h6>Senior backend developer</h6><span class="border px-2 ads-text">Ad</span>
                        </div>
                        <div class="border-top" >We selected the best tech projects to find you the ideal jobs. we offers salaries from $70K -100K so what you are waiting for just apply now with your latest resume.</div>
                    </div>
            </div>
            <div class="ads mb-2">
                    <div class="p-2 bg-white">
                        <div class="d-flex flex-row justify-content-between align-items-center">
                            <h6>Senior backend developer</h6><span class="border px-2 ads-text">Ad</span>
                        </div>
                        <div class="border-top" >We selected the best tech projects to find you the ideal jobs. we offers salaries from $70K -100K so what you are waiting for just apply now with your latest resume.</div>
                    </div>
            </div>
		</div>
    </div>

</div>

<script>
	$(document).on('click', '.like-btn', function () {
		
		//lay post_id
		var post_id = $(this).attr('post_id');
		
		//dung ajax goi servlet khong can chuyen trang
		var message = "";
        $.ajax({
            type: 'POST',
            url: 'LikeControl',
            data: 'post_id=' + post_id,
            async: false,
            error: function(response) {
            },
            success: function(response) {
            	message = response;
            }
        });
        
        //xu li front-end sau khi an like
        if(message != 'liked'){
            var old_likes = $(this).find("span").text();
            $(this).find("span").text(parseInt(old_likes) + 1);
            $(this).css("background-color","lightblue");
        }else{
        	var old_likes = $(this).find("span").text();
            $(this).find("span").text(parseInt(old_likes) - 1);
            $(this).css("background-color","white");
        }

	});
	
	$('.show-cmt-btn').click(function(e){
		
		//ngan ko chuyen trang khi an vao lien ket
	 	e.preventDefault();
		
		//lay post_id
		var post_id = $(this).parent().parent().find(".profile-engagements").find(".like-btn").attr('post_id');
		
		var str = "";
		
		$.ajax({
            type: 'GET',
            url: 'LoadCommentsControl',
            data: 'post_id=' + post_id,
            async: false, //dung de su dung bien str ben ngoai ajax
            error: function(response) {
            },
            success: function(response) {
             	$.each(response, function(index, item) {
             		
             		//chia json ban dau thanh 2 phan truoc va sau dau phay. Json tra ve co dang 'str,str'
            		var arr = item.toString().split(',');
            		
             		//noi cac bien vao chuoi html
            		var html = '<div style="margin-top:10px" class="d-flex align-items-center"><img class="rounded-circle img-responsive" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg" width="40" height="40"><div class="d-flex flex-column ml-2"><h6>' + arr[0] + '</h6>' + arr[1] + '</div></div>';
            		
            		//append cac div chua comment lai voi nhau
            		str += html;
                });
            }
        });
		
		//show comments
		$(this).parent().append(str);
		
		//an nut show comment
		$(this).css({'visibility':'hidden'});
	});
	
 	$(".send-comment").submit(function(e) {
		e.preventDefault();
		
		//dung ajax goi servlet khong can chuyen trang
	    $.ajax({
	    	type: 'POST',
	        url: 'Comment',
	        data: $(this).serializeArray(),
	        error: function(response) {
	        },
	        success: function(response) {
	        }
	    });

		//xu li front-end
		var cmt = $(this).children().find("#post_comment").val();
		var username = $(this).children().find("#post_comment").attr("username");
		var html = '<div style="margin-top:10px" class="d-flex align-items-center"><img class="rounded-circle img-responsive" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg" width="40" height="40"><div class="d-flex flex-column ml-2"><h6>' + username + '</h6>' + cmt + '</div></div>';
		$(this).parent().parent().parent().find(".show-comment").append(html); //append comment moi
		
		//clear text
		$(this).children().find('input:text').val(''); 
	});
 	
	//tu dong check da like bai viet hay chua, load nut like mau xanh
 	$(document).ready(function(){
 	   $('#feeds').children().each(function(){
 			var that = this;
 			//dung ajax goi servlet khong can chuyen trang
 		     $.ajax({
 		    	type: 'POST',
 		        url: 'CheckLike',
 		        data: 'post_id=' + $(this).attr("post_id"),
 		        error: function(response) {
 		        },
 		        success: function(response) {
 		        	if(response == 'liked'){
 		        		$(that).children().find(".profile-engagements").find(".like-btn").css("background-color","lightblue");
 		        	}else{
 		        		
 		        	}
 		        }
 		    });
 		});
 	});

 	$('#search-input').on('input', function() {
 		var text = $(this).val();
 	    
 	 	//dung ajax goi servlet khong can chuyen trang
		$.ajax({
            type: 'GET',
            url: 'SearchControl',
            data: 'username=' + text,
            async: false,
            error: function(response) {
            },
            success: function(response) {
            	$("#search-results").html(response);
            }
        });
 	});
	
</script>
</body>
</html>