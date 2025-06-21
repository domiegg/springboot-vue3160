<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','video_name') || $check_field('add','video_name') || $check_field('set','video_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="视频名称" prop="video_name">
					<el-input id="video_name" v-model="form['video_name']" placeholder="请输入视频名称"
							  v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','video_name')) || (!form['video_data_id'] && $check_field('add','video_name'))" :disabled="disabledObj['video_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','video_name')">{{form['video_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','cover')) || (!form['video_data_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','video') || $check_field('add','video') || $check_field('set','video')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="视频" prop="video">
					<el-upload v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','video')) || (!form['video_data_id'] && $check_field('add','video'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_video" :limit="1" accept="video/ogg,video/mp4,video/webm">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','video')">
						<el-button type="primary" @click="download(form['video'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','author') || $check_field('add','author') || $check_field('set','author')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="作者" prop="author">
					<el-input id="author" v-model="form['author']" placeholder="请输入作者"
							  v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','author')) || (!form['video_data_id'] && $check_field('add','author'))" :disabled="disabledObj['author_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','author')">{{form['author']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布时间" prop="release_time">
					<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','release_time')) || (!form['video_data_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','video_introduction') || $check_field('add','video_introduction') || $check_field('set','video_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="视频介绍" prop="video_introduction">
					<el-input type="textarea" id="video_introduction" v-model="form['video_introduction']" placeholder="请输入视频介绍"
						v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','video_introduction')) || (!form['video_data_id'] && $check_field('add','video_introduction'))" :disabled="disabledObj['video_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','video_introduction')">{{form['video_introduction']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['video_data_id'] && $check_field('set','details')) || (!form['video_data_id'] && $check_field('add','details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','details')" v-html="form['details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "video_data_id",
				url_add: "~/api/video_data/add?",
				url_set: "~/api/video_data/set?",
				url_get_obj: "~/api/video_data/get_obj?",
				url_upload: "~/api/video_data/upload?",

				query: {
					"video_data_id": 0,
				},

				form: {
					"video_name":'', // 视频名称
					"cover":'', // 封面
					"video":'', // 视频
					"author":'', // 作者
					"release_time":'', // 发布时间
					"video_introduction":'', // 视频介绍
					"details":'', // 详情
					"video_data_id": 0, // ID

				},
				disabledObj:{
					"video_name_isDisabled": false,
					"cover_isDisabled": false,
					"video_isDisabled": false,
					"author_isDisabled": false,
					"release_time_isDisabled": false,
					"video_introduction_isDisabled": false,
					"details_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},
			/**
			 * 上传视频
			 * @param {Object} param视频参数
			 */
			upload_video(param){
				this.uploadFile(param.file, "video");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["release_time"].indexOf("-")===-1){
          this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["release_time"]) > 9999){
					this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/video_data/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/video_data/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/video_data/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/video_data/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/video_data/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
