<template>
	<view class='box'>
		<view class="null-title"></view>
		<!-- 背景图 -->
		<image class="background-img" src="../../static/mediclpage/bg1.jpg"></image>

		<!-- 弹出框 -->
		<view class="input-row" v-if="showallergy" @touchmove.stop.prevent="moveHandle">
			<view class='b-window'>
				<text class="tc-title">药物过敏史：</text>
				<textarea adjust-positio="false" v-model="allergy2" maxlength="120" />
				<text id="allergylog" class="b-but1" @click="upddis($event)">提  交</text>
				<text class="line"></text>
				<text id="closeallergy" @click="modeify($event)" class="b-but2">取  消</text>
				</view>
		</view>

		<!-- 弹出框 -->
		<view class="input-row" v-if="showmedication" @touchmove.stop.prevent="moveHandle">
			<view class='b-window'>
				<text class="tc-title">常用药：</text>
				<textarea adjust-positio="false" v-model="medication2" maxlength="120" />
				<text id="medicationlog" class="b-but1" @click="upddis($event)">提  交</text>
				<text class="line"></text>
				<text id="closemedication" @click="modeify($event)" class="b-but2">取  消</text>
				</view>
		</view>

		<!-- 弹出框 -->
		<view class="input-row" v-if="showillog" @touchmove.stop.prevent="moveHandle">
			<view class='b-window'>
				<text class="tc-title">疾病史：</text>
				<textarea adjust-positio="false"  v-model="illog2" maxlength="120" />
				<text id="illog" class="b-but1" @click="upddis($event)">提  交</text>
				<text class="line"></text>
				<text id="closeillog" @click="modeify($event)" class="b-but2">取  消</text>
				</view>
		</view>

		<!-- 其余 -->
		<view class="top">
			<image class="top-img" src="../../static/mediclpage/ling.png"></image>
			<view class="top-title">
				病例档案
			</view>
			<scroll-view class="top-infor" scroll-y="true">
				病例编号：0{{id}}</br>
				药物过敏史：{{allergy}}</br>
				常用药：{{medication}}</br>
				疾病史：{{illog}}</br>
				<p>--------------------------------------</p>
				注：请点击下方按钮编辑您的病例档案。</br>此档案用于在您出现突发疾病或意外情况时给救助你的医生提供有效信息，以提高救援的效率。
			</scroll-view>
			<image id="openallergy" @click="modeify($event)" class="button-allergy-img" src="../../static/mediclpage/allergylog.png"></image>


			<image id="openmedication" @click="modeify($event)" class="button-medication-img" src="../../static/mediclpage/medicationlog.png"></image>


			<image id="openillog" @click="modeify($event)" class="button-illog-img" src="../../static/mediclpage/illog1.png"></image>

			<image class="button-help-img" src="../../static/mediclpage/button.png" @touchstart="buttonReq()"></image>

		</view>





	</view>
	</view>


</template>

<script>
	export default {
		data: {
			id: "xxx",
			allergy: "xxxxxxxxxxxxxxxx",
			medication: "xxxxxxxxxxxxxx",
			illog: "xxxxxxxxxxxxxxxxxxxxxx",
			allergy2: "xxxxxxxxxxxxxxxx",
			medication2: "xxxxxxxxxxxxxx",
			illog2: "xxxxxxxxxxxxxxxxxxxxxx",
			showallergy: false,
			showmedication: false,
			showillog: false,
			upcontent:"",
			button_type: 0 ,//按钮的点击次数
			longitude:0,//经度
			latitude:0//纬度
		},
		onLoad: function() {
			this.id = uni.getStorageSync("ID");
			this.allergy = this.allergy2 = uni.getStorageSync('allergylog');
			this.medication =this.medication2 = uni.getStorageSync('medicationlog');
			this.illog = this.illog2 = uni.getStorageSync('illog');
		},
		methods: {
			buttonReq() {
				// 震动
				this.button_type++;
				uni.vibrateLong();
				if(this.button_type>=3){
					//获取当前位置
					uni.getLocation({
						type: this.locationtype,
						success: function (res) {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
						this.longitude=res.longitude;
						this.latitude=res.latitude;
						}
					});
					//发送医疗求助信号
				uni.request({
					url: 'http://127.0.0.1:8080/sos/medical',
					// url: 'http://192.168.191.1:8080/user/login',
					data: {
						id: uni.getStorageSync("ID"),
						location: this.longitude+"#"+this.latitude,
						stuname: uni.getStorageSync("username"),
						stutel: uni.getStorageSync("telnumber"),
						illog: "药物过敏史："+this.allergy+"#"+"常用药："+this.medication+"#"+"疾病史："+this.illog
					}
				});
				uni.makePhoneCall({
					phoneNumber: uni.getStorageSync("matterstel") 
				});
				this.button_type=0;
				}
			},
			modeify(e){
				switch(e.currentTarget.id){
					case 'openallergy':
						this.showallergy=true;
						break;
					case 'closeallergy':
						this.allergy2 = this.allergy;
						this.showallergy=false;
						break;
					case 'openmedication':
						this.showmedication=true;
						break;
					case 'closemedication':
						this.medication2 =this.medication;
						this.showmedication=false;	
						break;
					case 'openillog':
						this.showillog=true;			
						break;
					case 'closeillog':
						this.illog2 = this.illog;
						this.showillog=false;			
						break;
				}
			},
			upddis(e){
				uni.showLoading({
					title: '请稍后...',
					mask: false
				});
				switch(e.currentTarget.id){
					case 'allergylog':
						this.upcontent=this.allergy2;
						break;
					case 'medicationlog':
						this.upcontent=this.medication2;
						break;
					case 'illog':
						this.upcontent=this.illog2;
						break;
				};
				
				uni.request({
					url: 'http://127.0.0.1:8080/user/uddis',
					// url: 'http://192.168.191.1:8080/user/infor',
					data: {
						field:e.currentTarget.id,
						content:this.upcontent,
						id: this.id
					},
					success: (res) => {
						if(res.data>0){
							
							switch(e.currentTarget.id){
								case 'allergylog':
									this.allergy=this.allergy2;
									this.showallergy=false;
									break;
								case 'medicationlog':
									this.medication=this.medication2;
									this.showmedication=false;	
									break;
								case 'illog':
									this.illog=this.illog2;
									this.showillog=false;
									break;
							};
							uni.hideLoading();
						}else{
							uni.hideLoading();
							uni.showToast({
								icon: 'none',
								title: '修改失败！'
							});
						}
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							icon: 'none',
							title: '网络服务异常！'
						});
					}
				})
			}
			
			
		}
	}
	

</script>

<style scoped>
	.box {
		width: 100%;
		/* background: #ecdde1; */
		opacity: 1;
	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.null-title {
		height: 10%;
	}

	.top {

		width: 92%;
		height: 50%;
		margin-left: 4%;
		margin-top: 3%;
		background: rgba(255, 255, 255, 1);
		box-shadow: 0px 1px 3px rgba(193, 193, 193, 1);
		opacity: 1;
		border-radius: 7px;
	}

	.top-img {
		width: 16px;
		height: 16px;
		margin-left: 5%;
		margin-top: 5%;
		float: left;

	}

	.top-title {
		width: 98px;
		height: 20px;
		font-size: 16px;
		font-family: PingFang SC;
		font-weight: bold;
		line-height: 22px;
		color: rgba(61, 61, 61, 1);
		opacity: 1;
		float: left;
		margin-left: 5%;
		margin-top: 5%;
	}

	.top-infor {
		width: 90%;
		height: 77%;
		font-size: 16px;
		font-family: PingFang SC;
		font-weight: 300;
		line-height: 22px;
		color: rgba(61, 61, 61, 1);
		opacity: 1;
		float: left;
		margin-top: 4%;
		margin-left: 5%;
	}


	.button-allergy-img {
		width: 41px;
		height: 40px;
		background: rgba(0, 0, 0, 0);
		opacity: 1;
		float: left;
		margin-top: 12%;
		margin-left: 3%;
	}

	.button-medication-img {
		width: 44px;
		height: 42px;
		background: rgba(0, 0, 0, 0);
		opacity: 1;
		float: left;
		margin-top: 12%;
		margin-left: 3%;
	}

	.button-illog-img {
		width: 40px;
		height: 43px;
		background: rgba(0, 0, 0, 0);
		opacity: 1;
		float: left;
		margin-top: 12%;
		margin-left: 3%;
	}

	.button-help-img {
		width: 60%;
		height: 27%;
		opacity: 1;
		float: left;
		margin-top: 15%;
		margin-left: 20%;
	}

	.button-illog-img:active,
	.button-medication-img:active,
	.button-allergy-img:active {
		margin-top: 10%;
		margin-bottom: 2%;
	}

	.button-help-img:active {
		width: 57%;
		height: 26%;
		margin-top: 16%;
		margin-left: 22%;

	}

	/* 	.button-help-img:after {
		width: 60%;
		height: 27%;
		opacity: 1;
		float: left;
		margin-top: 15%;
		margin-left: 20%;
	} */
	
	textarea{
		width: 90%;
		height: 200upx;
		margin-top: 5upx;
		margin-left: 5%;
		font-weight: 400;
		line-height: 22upx;
		color: rgba(61, 61, 61, 1);
		opacity: 1;
		float: left;
		border: 1upx solid #cccccc;
	}
	
	.tc-title{
		margin-left: 5%;
		float: left;
		font-weight: 500;
	}
	
	.b-window{
		background-color: #FFFFFF;
		width:60%;
		height:330upx;
		opacity: 1;
		margin-top:60%;
		margin-left: 20%;
		box-shadow: 0px 1upx 3upx rgba(193, 193, 193, 1);
		border-radius: 15upx;
	}
	
	.input-row{
		top:0upx;
		position: absolute;
		z-index: 2;
		width: 100%;
		height:100%;
		margin-right: 100upx;
		background: rgba(0, 0, 0, 0.5);
		border-radius: 15upx;
		float: left;
		box-shadow: 0px 1px 3px rgba(193, 193, 193, 1);
	}
	
	.b-but1,
	.b-but2{
		margin-top:10upx;
		width: 40%;
		height: 55upx;
		float:left;
		font-weight: 400;
		margin-left: 5%;
		text-align:center;
		font-weight: 500;
			/* background-color: #; */
	}
	.b-but1:active,
	.b-but2:active{
		border: 1upx #00B26A solid;
		width:39% ;
	}
	.line{
		margin-top:10upx;
		width: 1%;
		height: 50upx;
		float:left;
		margin-left: 3%;
		text-align:center;
		background-color: #10b42b;
	}
</style>
