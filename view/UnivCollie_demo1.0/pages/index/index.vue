<template>
	<view class="box">
		<image class="background-img" src="../../static/mediclpage/bg1.jpg"></image>
		<view class="title">
			打卡时间:{{pun_time}}
		</view>
		<view class="foot">
			考勤管理员：18313023631
		</view>
		<view class="content" @touchstart="punchf()">
			<image class="pun-but" src="../../static/pbut.png"></image>
		</view>


	</view>
</template>

<script>
	export default {
		data: {
			pun_time: "12:10~24:18",
			begin_hour: 0,
			over_hour: 0,
			begin_minute: 0,
			over_minute: 0
		},
		onLoad: function() {
			this.pun_time = uni.getStorageSync('begintime') + '~' + uni.getStorageSync('overtime');
			this.begin_hour = this.pun_time.slice(0, 2);
			this.over_hour = this.pun_time.slice(6, 8);
			this.begin_minute = this.pun_time.slice(3, 5);
			this.over_minute = this.pun_time.slice(9);
		},
		onPullDownRefresh() {
			uni.request({
				
				// url: 'http://127.0.0.1:8080/data/getInfor',
				url: 'http://148.70.148.17:8080/data/getInfor',
				// url: 'http://192.168.191.1:8080/user/infor',
				success: (res) => {
					this.pun_time = res.data.begintime_h + ':' + res.data.begintime_m + '~' + res.data.overtime_h + ':' + res.data.overtime_m;
					this.begin_hour = this.pun_time.slice(0, 2);
					this.over_hour = this.pun_time.slice(6, 8);
					this.begin_minute = this.pun_time.slice(3, 5);
					this.over_minute = this.pun_time.slice(9);
					uni.stopPullDownRefresh();
				},
				fail: (res) => {
					uni.showToast({
						icon: 'none',
						title: '网络服务异常！'
					});
					uni.stopPullDownRefresh();
				}
			});
		},
		methods: {
			punchf() {
				// 震动 
				uni.vibrateLong();
				var now_time = new Date();
				console.log("当前时间为：" + now_time.getHours() + ":" + now_time.getMinutes());
				console.log(this.begin_hour + "--" + this.over_hour + "--" + this.begin_minute + "--" + this.over_minute);
				if (((now_time.getHours() == this.begin_hour && now_time.getMinutes() >= this.begin_minute) || (now_time.getHours() >
						this.begin_hour)) && ((now_time.getHours() == this.over_hour && now_time.getMinutes() <= this.over_minute) || (
						now_time.getHours() < this.over_hour))) {
					uni.showLoading({
						title: '请稍后~',
						mask: true
					});
					uni.getLocation({
						type: this.locationtype,
						success: function(res) {
							console.log('当前位置的经度：' + res.longitude);
							console.log('当前位置的纬度：' + res.latitude);
							// 打卡
							uni.request({
								// 148.70.148.17
								// url: 'http://127.0.0.1:8080/user/punch',
								url: 'http://148.70.148.17:8080/user/punch',
								data: {
									id: uni.getStorageSync("ID"),
									longitude: res.longitude,
									latitude: res.latitude
								},
								success: (res) => {
									if (res.data > 0) {
										if (res.data == 1) {
											uni.showToast({
												icon: 'none',
												title: '打卡成功！'
											});
										} else {
											uni.showToast({
												icon: 'none',
												title: '打过卡了哟！'
											});
										}
									} else {
										uni.showToast({
											icon: 'none',
											title: '请在打卡范围内打卡！'
										});
									}
									uni.stopPullDownRefresh();
								},
								fail: (res) => {
									uni.showToast({
										icon: 'none',
										title: '网络服务异常！'
									});
									uni.stopPullDownRefresh();
								}
							});
						},
						fail: (res) => {
							uni.showToast({
								icon: 'none',
								title: '网络服务异常！'
							});
							uni.stopPullDownRefresh();
						}
					});


				} else {
					uni.showToast({
						icon: 'none',
						title: '还不到打卡时间哟！'
					});
				}
			}
		}
	}
</script>

<style>
	.box {

		height: 100%;
		width: 100%
	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.title {
		font-size: 35upx;
		margin-top: 18%;
		height: 60upx;
		width: 50%;
		margin-right: 300upx;
		background-color: #FFFFFF;
		border-radius: 0 50upx 50upx 0;
		color: #00CED1;
	}

	.content {
		margin-top: 50%;
		margin-left: 36%;
		height: 220upx;
		width: 28%;
		background: #FFFFFF;
		border-radius: 50%;
		color: #00CED1;
		font-size: 50upx;
	}

	.pun-but {
		margin-top: 2%;
		width: 95%;
		height: 98%;
	}

	.foot {
		font-size: 35upx;
		margin-top: 3%;
		height: 60upx;
		width: 60%;
		margin-right: 300upx;
		background-color: #FFFFFF;
		border-radius: 0 50upx 50upx 0;
		color: #00CED1;
	}
</style>
