<template>
	<view class="content">

		<view class="logo-row">
			<view class="logo-row-img">
				<image src="../../static/login_background.png"></image>
			</view>
		</view>
		<view class="input-group">
			<view class="input-row border">
				<text class="title">手机号：</text>
				<input type="number" v-model="telnumber" placeholder="请输入手机号">
			</view>
			<view class="input-row border">
				<text class="title">学号：</text>
				<input type="number" v-model="studentid" placeholder="请输入学号">
			</view>
			<view class="input-row border">
				<text class="title">姓名：</text>
				<input type="text" v-model="username" placeholder="请输入姓名">
			</view>
			<view class="input-row border">
				<text class="title">密码：</text>
				<input type="text" password="true" v-model="password" placeholder="请输入密码">
			</view>
			<view class="input-row border">
				<text class="title">验证码：</text>
				<input type="text" v-model="code" placeholder="请输入验证码">
			</view>
		</view>

		<view class="btn-row">
			<button type="primary" class="primary" @tap="getRegisterIfm(telnumber,studentid,username,password,code)">{{button}}</button>
		</view>


		<view class="action-row">
			<navigator url="../register/register">用户条款</navigator>
		</view>

	</view>
</template>

<script>
	export default {
		data: {
			telnumber: '',
			studentid: '',
			password: '',
			username: '',
			code: '',
			button: '获取验证码',
		},
		methods: {
			//用于注册
			getRegisterIfm(telnumber, studentid, username, password, code) {
				if ((code == '' || code == "0519") && this.button.charAt(this.button.length - 1) == '码') {
					uni.showToast({
						icon: 'none',
						title: '验证码是0519'
					});
					this.button = '注册';
					return;
				}
				if (verification(telnumber, studentid, username, password, code) == 0) {
					return;
				}
				if (code != '0519' && this.button.charAt(this.button.length - 1) == '册') {
					uni.showToast({
						icon: 'none',
						title: '请重新获取验证码'
					});
					this.button = '获取验证码';
					return;
				}
				uni.showLoading({
					title: '请稍后...',
					mask: 'true'
				});
				uni.request({
					url: 'http://127.0.0.1:8080/user/register',
					// url: 'http://192.168.191.1:8080/user/register',
					method: 'POST',
					data: {
						telnumber: telnumber,
						studentid: studentid,
						username: username,
						password: password,
					},
					success: (res) => {
						console.log(res.data);
						if (res.data == 'Success') {
							uni.setStorageSync('telnumber', telnumber);
							uni.setStorageSync('password', password);
							uni.hideLoading();
							uni.reLaunch({
								url: '../login/login'
							});
						} else {
							if (res.data.length < 20) {
								uni.hideLoading();
								uni.showToast({
									icon: 'none',
									title: res.data
								});
							}
							if (res.data.length > 20) {
								uni.hideLoading();
								uni.showToast({
									icon: 'none',
									title: '404！请联系管理员'
								});
							}
						}
					}
				});
			}

		}
	}

	function verification(telnumber, studentid, username, password, code) {
		var teltest = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var idtest = /^[0-9]+$/;
		var nametest = /^[\u0391-\uFFE5]+$/;
		var pwdtest = /^[0-9a-zA-Z]*$/;
		if (telnumber.length == 0 || telnumber.length != 11 || !teltest.test(telnumber)) {
			uni.showToast({
				icon: 'none',
				title: '请输入有效的手机号'
			});
			return 0;
		}
		if (studentid.length == 0 || !idtest.test(studentid)) {
			uni.showToast({
				icon: 'none',
				title: '请输入有效的学号'
			});
			return 0;
		}
		if (username.length == 0 || !nametest.test(username)) {
			uni.showToast({
				icon: 'none',
				title: '请输入中文名'
			});
			return 0;
		}
		if (password.length == 0 || !pwdtest.test(password) || password.length < 6) {
			uni.showToast({
				icon: 'none',
				title: '密码6位以上的字母加数字'
			});
			return 0;
		}
		return 1;

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
