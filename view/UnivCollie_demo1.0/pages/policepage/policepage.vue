<template>
	<view class="box">
		<view class="broadcast">您的当前位置，下拉刷新重新定位</view>
		<image class="background-img" src="../../static/mediclpage/bg1.jpg"></image>
		<map id="polmap" class="map1" :latitude="latitude" :longitude="longitude" :markers="covers" :show-location="true"></map>

		<image class="button1" src="../../static/policpage/button1.png" id="b1" @touchstart="buttonReq($event)"></image>
		<image class="button2" src="../../static/policpage/button2.png" id="b2" @touchstart="buttonReq($event)"></image>
	</view>
</template>

<script>
	export default {
		data: {
			locationtype: 'gcj02',
			longitude: 102.847785,
			latitude: 24.833456,
			button_type: 0,
			covers: [{
				longitude: 0,
				latitude: 0,
				iconPath: '../../static/location@3x.png'
			}]
		},
		onLoad() {
			uni.getLocation({
				type: this.locationtype,
				success: function(res) {
					console.log('当前位置的经度：' + res.longitude);
					console.log('当前位置的纬度：' + res.latitude);
					uni.setStorageSync("longitude", res.longitude);
					uni.setStorageSync("latitude", res.latitude);
				}
			});
			this.longitude = uni.getStorageSync("longitude");
			this.latitude = uni.getStorageSync("latitude");
			var mapContext = uni.createMapContext('polmap', this);
			mapContext.moveToLocation();
			this.$set(this.covers, 0, Object.assign(this.covers[0], {
				longitude: uni.getStorageSync("longitude"),
				latitude: uni.getStorageSync("latitude")
			}));
		},
		methods: {
			buttonReq(e) {
				// 震动 
				// if(this.button_type==0){
				setTimeout(() => {
					this.button_type = 0
				}, 1000); //箭头函数解决data失效问题			
				this.button_type++;
				uni.vibrateLong();
				if (this.button_type >= 3) {
					//获取当前位置
					uni.getLocation({
						type: this.locationtype,
						success: function(res) {
							console.log('当前位置的经度：' + res.longitude);
							console.log('当前位置的纬度：' + res.latitude);
							uni.setStorageSync("longitude", res.longitude);
							uni.setStorageSync("latitude", res.latitude);
						}
					});
					//发送警务求助信号
					uni.request({
						url: 'http://148.70.148.17:8080/sos/police',
						// url: 'http://192.168.191.1:8080/user/login',
						data: {
							id: uni.getStorageSync("ID"),
							location: uni.getStorageSync("longitude") + "#" + uni.getStorageSync("latitude"),
							stuname: uni.getStorageSync("username"),
							stutel: uni.getStorageSync("telnumber"),
							illog: "药物过敏史：" + this.allergy + "#" + "常用药：" + this.medication + "#" + "疾病史：" + this.illog
						}
					});
					if (e.currentTarget.id == "b2") {
						uni.makePhoneCall({
							phoneNumber: uni.getStorageSync("pol_tel")
						});
					}
					this.button_type = 0;
				}
			}
		},
		onPullDownRefresh() {
			uni.getLocation({
				type: this.locationtype,
				success: function(res) {
// 					console.log('当前位置的经度2：' + this.longitude);
// 					console.log('当前位置的纬度2：' + this.latitude);
					uni.setStorageSync("longitude", res.longitude);
					uni.setStorageSync("latitude", res.latitude);
				}
			});
			this.longitude = uni.getStorageSync("longitude");
			this.latitude = uni.getStorageSync("latitude");
			var mapContext = uni.createMapContext('polmap', this);
			mapContext.moveToLocation();
			this.$set(this.covers, 0, Object.assign(this.covers[0], {
				longitude: uni.getStorageSync("longitude"),
				latitude: uni.getStorageSync("latitude")
			}));
			uni.stopPullDownRefresh();
		}
	}
</script>

<style>
	.box {
		left: 0px;
		top: 0px;
		width: 100%;
		height: 100%;
	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.broadcast {
		height: 60upx;
		background: #00CED1;
		width: 90%;
		margin-left: 5%;
		margin-top: 18%;
		font-size: 13px;
		color: #F7F7F7;
		animation: 10s wordsLoop linear infinite normal;
	}

	.map1 {
		float: left;
		width: 90%;
		height: 700upx;
		margin-left: 5%;
		margin-top: 0%;
	}

	.button1,
	.button2 {
		width: 40%;
		height: 100upx;
		margin-top: 10%;
		margin-left: 6%;
		float: left;
	}

	.button1:active {
		width: 36%;
		height: 85upx;
		margin-top: 11%;
		margin-left: 8%;
		margin-right: 2%;
	}

	.button2:active {
		width: 36%;
		height: 85upx;
		margin-top: 11%;
		margin-left: 8%;

	}
</style>
