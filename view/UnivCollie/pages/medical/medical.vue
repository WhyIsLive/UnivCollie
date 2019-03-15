<template>
	<view class='box'>
		<image class="background-img" src="../../static/mediclpage/dabai2.png"></image>
		<view class="top">
			<view class="button-list">
				<view class="button-allergy">
					<image class="button-allergy-img" src="../../static/mediclpage/allergylog.png"></image>
				</view></br>
				<view class="button-medication">
					<image class="button-medication-img" src="../../static/mediclpage/medicationlog.png"></image>
				</view>
				<view class="button-illog">
					<image class="button-illog-img" src="../../static/mediclpage/illog1.png"></image>
				</view>
			</view>

		</view>

		<view class="content">
			<view class="button-help">
				<image class="button-help-img" src="../../static/mediclpage/button2.png"></image>
			</view>
		</view>


	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style scoped>
	.box {
		width: 100%;
		align-items: center;
	}

	.background-img {
		z-index: -1;
		position: absolute;
		top: 0upx;
		width: 100%;
		height: 100%;
	}

	.top {
		width: 98%;
		height: 35%;
		/* border: 3upx solid #333333; */
		margin-left: auto;
		margin-right: auto;
		margin-top: 30px;
	}

	.content {
		width: 98%;
		height: 550upx;
		/* border: 3upx solid #333333; */
		margin-left: auto;
		margin-right: auto;
	}

	.button-list {
		width: 170upx;
		height: 100%;
		border-radius: 10px;
		margin-left: 10upx;

		background-color: #EFEFF4;
	}

	.button-help {
		width: 47%;
		height: 52%;
		margin-top:55% ;
		margin-left: auto;
		margin-right: auto;
	}

	.button-help-img {
		width: 100%;
		height: 100%;
	}

	.button-allergy,
	.button-medication,
	.button-illog {
		width: 80%;
		height: 33%;
		margin-left: auto;
		margin-right: auto;
		
	}
	
	.button-medication,
	.button-illog{
		border-top: 1upx solid #333333;
	}

	.button-allergy-img,
	.button-medication-img,
	.button-illog-img {
		width: 75%;
		height: 75%;
		margin-left: 13%;
		margin-top: 10%;
	}
</style>
