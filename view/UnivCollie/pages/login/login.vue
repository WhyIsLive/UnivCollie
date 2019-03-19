<template>
	<view class="content">

		<view class="logo-row">
			<view class="logo-row-img">
				<image src="../../static/login_background.png"></image>
			</view>
		</view>
		<view class="input-group">
			<view class="input-row border">
				<text class="title">账号：</text>
				<input type="text" v-model="telnumber" placeholder="请输入手机号">
			</view>
			<view class="input-row">
				<text class="title">密码：</text>
				<input type="text" password="true" v-model="password" placeholder="请输入密码">
			</view>
		</view>

		<view class="btn-row">
			<button type="primary" class="primary" @tap="bindLogin(telnumber,password)">登录</button>
		</view>

		<view class="action-row">
			<navigator url="../register/register">注册账号</navigator>
			<text>|</text>
			<navigator url="../pwd/pwd">忘记密码</navigator>
		</view>

	</view>
</template>

<script>
	export default {
		data: {
			telnumber: "",
			password: ""
		},
		//自动登陆
		onLoad: function() {
			var telnumber = uni.getStorageSync("telnumber");
			var password = uni.getStorageSync("password");
			if (telnumber.length != 0 && password.length != 0) {
				uni.showLoading({
					title: '登陆中',
					mask: 'true'
				});
				uni.request({
					url: 'http://127.0.0.1:8080/user/login',
					// url: 'http://192.168.191.1:8080/user/login',
					method: 'POST',
					data: {
						telnumber: telnumber,
						password: password
					},
					success: (res) => {
						console.log(res.data);
						if (res.data.ID == 0) {
							uni.hideLoading();
							uni.showToast({
								icon: 'none',
								title: '登陆失败！'
							});
							return;
						}
						uni.setStorageSync('ID', res.data.ID);
						uni.setStorageSync('studentid', res.data.studentid);
						uni.setStorageSync('username', res.data.username);
						uni.setStorageSync('telnumber', telnumber);
						uni.setStorageSync('password', password);
						uni.hideLoading();
						uni.reLaunch({
							url: '../userinfor/userinfor'
						});
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							icon: 'none',
							title: '网络服务异常！'
						});
					}
				})
			};
			uni.getProvider({
				service: 'push',
				success: function(res) {
					console.log(res.provider)
			
					// 个推的名称为 igexin
					if (~res.provider.indexOf('igexin')) {
						uni.subscribePush({
							provider: 'igexin',
							success: function(res) {
								console.log('success:' + JSON.stringify(res));
							}
						});
					}
				}
			});
		},
		
		methods: {
			//用于登陆
			bindLogin(telnumber, password) {
				if (login_null_test(telnumber, password) == 1)
					return;
				//登陆空值验证
				uni.showLoading({
					title: '登陆中',
					mask: 'true'
				});
				uni.request({
					url: 'http://127.0.0.1:8080/user/login',
					// url: 'http://192.168.191.1:8080/user/login',
					method: 'POST',
					data: {
						telnumber: telnumber,
						password: password
					},
					success: (res) => {
						console.log(res.data);
						if (res.data.ID == 0) {
							uni.hideLoading();
							uni.showToast({
								icon: 'none',
								title: '登陆失败！'
							});
							return;
						}
						uni.setStorageSync('ID', res.data.ID);
						uni.setStorageSync('telnumber', telnumber);
						uni.setStorageSync('password', password);
						uni.setStorageSync('username', res.data.username);
						uni.setStorageSync('studentid', res.data.studentid);
						uni.hideLoading();
						uni.reLaunch({
							url: '../userinfor/userinfor'
						});
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							icon: 'none',
							title: '网络服务异常！'
						});
					}
				});
			}
		}
	}



	function login_null_test(username, password) {
		//登陆空值验证
		if (username == '') {
			uni.showToast({
				icon: 'none',
				title: '账号不能为空'
			});
			return 1;
		}
		if (password == '') {
			uni.showToast({
				icon: 'none',
				title: '密码不能为空'
			});
			return 1;
		}
		return 0;
	}
</script>

<style>
	.content {
		display: flex;
		flex: 1;
		flex-direction: column;
		padding: 20px;
		background-color: #efeff4;
	}

	.input-group {
		background-color: #ffffff;
		margin-top: 5%;
		position: relative;
		border-radius: 10px 10px;
	}

	.input-group::before {
		position: absolute;
		right: 0;
		top: 0;
		left: 0;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}

	.input-group::after {
		position: absolute;
		right: 0;
		bottom: 0;
		left: 0;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}

	.input-row {
		display: flex;
		flex-direction: row;
		position: relative;
	}

	.input-row .title {
		width: 20%;
		height: 30px;
		min-height: 30px;
		padding: 15px 0;
		padding-left: 30px;
		line-height: 30px;
	}

	.input-row input {
		width: 80%;
		height: 30px;
		min-height: 30px;
		padding: 15px 0;
		padding-right: 30px;
		line-height: 30px;
	}

	.input-row.border::after {
		position: absolute;
		right: 0;
		bottom: 0;
		left: 15px;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}

	.btn-row {
		margin-top: 5%;
		padding: 20px;
	}

	button.primary {
		background-color: #00CED1;
	}

	.action-row {
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.action-row navigator {
		color: #00CED1;
		padding: 0 20px;
	}



	.logo-row {
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.logo-row image {
		border-radius: 10px 10px;
		width: 100px;
		height: 100px;
	}
</style>
