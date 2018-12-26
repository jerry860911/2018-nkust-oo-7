<!DOCTYPE HTML>

<html lang="zh-TW">
	<head>
		<title>醫院病床查詢系統</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="cache-control" content="no-cache"> 
		<meta http-equiv="pragma" content="no-cache"> <meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="assets/css/main.css" />		
	</head>
	<body>
		<!-- Header -->
			<header id="header" class="alt">
				<div class="logo"><a href="index.php">hospital searching <span>by NKUST</span></a></div>
				<a href="#menu">Menu</a>
			</header>
		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.php">首頁</a></li>
					<li><a href="addmember.php">申請帳號</a></li>					
				</ul>
			</nav>
		<!-- Banner -->
			<section id="banner">
				<div class="inner">			
					<header class="align-center">
						<form  action="./login.php" method="POST">
							<p>帳號<input type="text" name="Id" id="Id"/></p>
							<p>密碼<input type="password" name="Password" id="Password"/></p> 	
							<p><input type="Submit" value="登入"></p>
							<input type="reset" value="清除">
						</form>
					</header>
					<a href="addmember.php" class="button special fit">申請帳號</a>
				</div>
			</section>	
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li>
					</ul>
					<p>&copy; Untitled. All rights reserved. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.</p>
				</div>
			</footer>
		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
	</body>
</html>