<!DOCTYPE HTML>
<html lang="zh-TW">
	<head>
		<title>醫院病床查詢系統</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets\css\main.css" />
	</head>
	<body class="subpage">
		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="index.php">hospital searching <span>by NKUST</span></a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.php">首頁</a></li>
					<li><a href="member00.php">申請帳號</a></li>					
				</ul>
			</nav>
		<div id="main">
			<section class="wrapper style1">
            <div class="inner">
	            <!-- Form -->
				<header class="align-center">
					<h2>會員資料</h2>							
				</header>	
				<form  method="post" action="./add.php">
					<div class="row uniform">
					    <div class="6u 12u$(xsmall)">
							<input type="text" name="Id" id="Id" value="" placeholder="帳號"  maxlength='20' required />
						</div>
						<div class="6u 12u$(xsmall)">
	                       <input type="Password" name="Password" value="" placeholder="密碼" maxlength='20' required />
						</div>											
						<div class="6u 12u$(xsmall)">
							<input  type="text" name="Phone" id="Phone" placeholder="手機號碼 ex:0912345678" pattern="[0-9]{10}" required />
						</div>
						<div class="6u$ 12u$(xsmall)">
							<input type="text" name="Birthday" id="Birthday" value="" placeholder="生日 ex:1990-11-11" pattern='\d{4}[\-]\d{2}[\-]\d{2}' required />
						</div>                           									
					    <div class="12u$">
							<div class="select-wrapper">
								<select  name="Gender" id="Gender">	
									<option value="男">男性</option>
									<option value="女">女性</option>
								</select>
							</div>
						</div>
						<div class="12u$">
							<input type="email" name="Email" id="Email" value="" placeholder="E-Mail" required />
						</div>						
							<ul class="actions">
								<li><input type="submit" value="註冊" /></li>
								<li><input type="reset" value="清除" class="alt" /></li>
							</ul>							
						</div>
					</form>											
	<?php
	/*
	$datetime = date ("Y-m-d H:i:s",strtotime("-1day +7 hours")) ; 
	echo $datetime ;
	*/											
	?>
					</div>
			</section>
		</div>
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li>
					</ul>
					
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