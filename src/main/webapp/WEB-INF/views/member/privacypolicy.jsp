<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title>privacypolicy Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <!--Custom styles-->
     <link rel="stylesheet" href="<%=request.getContextPath() %>/css/privacypolicy.css">
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
                <h3>Privacy Policy</h3>
            </div>
            <div class="card-body">
                PERSONAL INFORMATION
                <br>Illegal Parking may collect and store personal information from you, including, but not limited to, the following information (collectively, together with any other information Parking Illegal may collect in relation to your use of
                the Site, "Personal Information"): names, email address, phone number and physical contact information; vehicle information such as license plate information, make/model/year of your vehicle; depending on the service used, sometimes financial
                information, such as credit card or bank account numbers; Information you submit when creating or using your User Account, including user login and password information; the location and IP address from which you access the Site; the name
                of the domain from which you access the internet; the type of device, browser and operating system used to access the Site; the date and time you access the Site, duration and stop time of such access; the information about any person
                or entity from whom you were referred; the pages, files, documents and links that you visit; the internet address of the website from which you linked to the Site; any other information about your visit to the Site, Reservations you make
                or your use of the Marketing Services generally; and information about you received from Licensors that you interact with as a result of our Marketing Services, or from other third-parties to whom you have previously provided information.
                Some Personal Information may be automatically gathered so that we can monitor the use of the Site in order to make improvements and better serve visitors. This site may use proprietary analytics systems to help improve usability and the
                customer experience. We may record mouse clicks, mouse movements, scrolling activity, user agent, browser version, operating system, browser extensions installed, browser features enabled, and other non-personally identifiable behavioral
                and non-behavioral information. You can choose to disable these features by electing to use the private browser setting and/or disabling javascript. Note, that doing so may disable other features that this site may employ. <br><br>HOW
                WE USE YOUR PERSONAL INFORMATION <br> In general, we do not share Personal Information that is personally identifiable to you, except where we have your express permission or where required by law. At times, in order to provide products
                and services or market to customers, we may make certain Personal Information available to strategic partners with whom we work. We may share Personal Information with parties who provide services such as information processing, extending
                credit, fulfilling customer orders, delivering products, managing and enhancing customer data, providing customer service, assessing interest in our products and services, and conducting customer research or satisfaction surveys. Except
                as required pursuant to law, legal process, litigation, or requests from public and governmental authorities, we only share Personal Information to provide or improve our products, services and advertising. We will not share your Personal
                Information with third parties for their marketing purposes. In the event of a Parking Illegal’s reorganization, merger, or sale, we may transfer any and all Personal Information we collect to the relevant third party. As part of the services
                we provide, we give certain Personal Information to Licensors in order for such Licensors to, among other things, provide their services in connection with facilitating Reservations, and for the Licensors' own business uses, including
                marketing to you. Licensors’ use and sharing of your Personal Information will be subject to each Licensor’s privacy policy. Parking Illegal is not responsible for any Licensor’s use or sharing of your information.
            </div>
            </form>
        </div>

    </div>
</div>
</div>
</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>


</body>

</html>