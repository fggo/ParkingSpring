<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title>termsofuse Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/termsofuse.css">
    <link href="https://fonts.googleapis.com/css?family=Rubik&display=swap" rel="stylesheet">

    <!-- jQuery -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



</head>


<header>
    <nav class="site-header navbar navbar-expand-lg navbar-dark fixed-top py-0">
        <!-- navbar navbar-expand-md navbar-dark fixed-top bg-dark -->
        <div class="container">
            <a class="navbar-brand " href="#">
                <img src="<%=request.getContextPath() %>/images/logo_white.png">
            </a>
            <form id="nav-searchbar" class="navbar-form" style="width: 410px">
                <div class="input-group">
                    <input type="search" placeholder="Search for Parking" aria-describedby="button-addon5" class="form-control">
                    <div class="input-group-append">
                        <button id="button-addon5" type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapsible" aria-controls="navbarCollapsible" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
            <div class="collapse navbar-collapse" id="navbarCollapsible">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="#" class="nav-link menu-item">Review</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link menu-item">About</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link menu-item">Help</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle menu-item mr-2" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Hi! Foo!</a>
                        <div class="dropdown-menu mt-1" aria-labelledby="dropdown01">
                            <a class="dropdown-item" href="#">My Reservations</a>
                            <a class="dropdown-item" href="#">Account</a>
                            <a class="dropdown-item" href="#">Payment Method</a>
                            <a class="dropdown-item" href="#">Credit Balance</a>
                            <a class="dropdown-item" href="#">My Vehicle</a>
                            <a class="dropdown-item" href="#">Bookmark</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <button type="button" class="btn btn-sm btn-dark mt-1">Sign Up</button>
                        <!-- <a class="btn btn-outline-light m-2 " href="#">Sign up</a> -->
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container sns">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3>Terms of Use</h3>
            </div>
            <div class="card-body">
                Thank you for visiting the Illegal Parking website at illegalparking.com and associated software application(s) (collectively, the "Site"). Your use of the information, materials, text, images and other content on the Site is subject to the terms and
                conditions below, which we may revise from time to time without notice. Please read these terms and conditions carefully. BY ACCESSING THE SITE, YOU ACKNOWLEDGE AND AGREE THAT YOU HAVE READ, UNDERSTAND AND AGREE TO BE BOUND BY THESE GENERAL
                WEBSITE TERMS AND CONDITIONS (THESE "TERMS AND CONDITIONS"). PLEASE REVIEW THE FOLLOWING SECTIONS OF THESE TERMS AND CONDITIONS CAREFULLY: (A) DISPUTE RESOLUTION, (B) CLASS ACTION WAIVER, (C) LIMITATION OF LIABILITY, (D) INDEMNITY AND
                (E) RELEASE. IF YOU DO NOT AGREE TO THESE TERMS AND CONDITIONS, IMMEDIATELY STOP USING THE SITE AND DO NOT USE ANY illegal parking SERVICE, PARTICIPATE IN ANY PROGRAM OR PURCHASE ANY PRODUCT OR OTHER GOOD OR SERVICE OFFERED THROUGH THE
                SITE. If you fail to comply with these Terms and Conditions, your permission to use the Site automatically terminates and we may disable your use of the Site. The provisions of these Terms and Conditions apply equally to, and are for the
                benefit of, illegal parking service, its parent, subsidiaries, affiliates, Licensors, and its third-party content providers and licensors, and each shall have the right to assert and enforce such provisions directly.
                <br><br> Unless expressly stated otherwise herein, the services provided by Parking Ilegal (the "Marketing Services") are limited to informing users of the location and potential availability of certain parking spots ("Parking Spots")
                and facilitating Reservations (as defined below) by displaying Parking Spots, processing Reservations and collecting fees on behalf of Licensors (as defined below). Any fees and/or consideration paid to Parking Illegal is paid to Parking
                Illegal in connection with these Marketing Services unless otherwise indicated. To be clear, Parking Illegal does not: (i) negotiate terms of licenses or settle disputes between users of the Site; (ii) provide a parking service; (iii)
                operate a parking lot or other parking facility; (iv) otherwise accept custody of motor vehicles; or (v) provide services other than the Marketing Services (collectively, "Excluded Services"). The Excluded Services are provided by Licensors
                who are neither our agents nor acting on our behalf and we have no responsibility whatsoever regarding the safety of persons or property in the Parking Spots or any facility where the Parking Spots are located found through use of the
                Site. We are not responsible for any consequences arising from the Excluded Services, including, without limitation, consequences arising from not being able to park or delays associated with parking, road closures or changing traffic
                conditions. Although we do not guarantee the availability of a specific Parking Spot, if a booked Parking Spot is unavailable at your time of reservation, we will attempt to locate an alternative Parking Spot, provide you with a refund
                and/or otherwise resolve any issues in accordance with our standard customer service policies and procedures. The Marketing Services and Site are not intended for use by persons under the age of 13. If you are under 13, you may not use
                either the Marketing Services or the Site. You agree to comply with all applicable laws when accessing or using our Services, and you may only access or use the Services for lawful purposes.
            </div>
            </form>
        </div>

    </div>
</div>
</div>
</div>





</body>

</html>