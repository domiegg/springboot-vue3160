<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title') || $check_field('add','title') || $check_field('set','title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题" prop="title">
					<el-input id="title" v-model="form['title']" placeholder="请输入标题"
							  v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','title')) || (!form['development_history_id'] && $check_field('add','title'))" :disabled="disabledObj['title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title')">{{form['title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','cover')) || (!form['development_history_id'] && $check_field('add','cover'))">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','particular_year') || $check_field('add','particular_year') || $check_field('set','particular_year')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年份" prop="particular_year">
					<el-input id="particular_year" v-model="form['particular_year']" placeholder="请输入年份"
							  v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','particular_year')) || (!form['development_history_id'] && $check_field('add','particular_year'))" :disabled="disabledObj['particular_year_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','particular_year')">{{form['particular_year']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','place') || $check_field('add','place') || $check_field('set','place')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="地点" prop="place">
					<el-input id="place" v-model="form['place']" placeholder="请输入地点"
							  v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','place')) || (!form['development_history_id'] && $check_field('add','place'))" :disabled="disabledObj['place_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','place')">{{form['place']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','dominating_figure') || $check_field('add','dominating_figure') || $check_field('set','dominating_figure')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主要人物" prop="dominating_figure">
					<el-input id="dominating_figure" v-model="form['dominating_figure']" placeholder="请输入主要人物"
							  v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','dominating_figure')) || (!form['development_history_id'] && $check_field('add','dominating_figure'))" :disabled="disabledObj['dominating_figure_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','dominating_figure')">{{form['dominating_figure']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cause') || $check_field('add','cause') || $check_field('set','cause')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="起因" prop="cause">
					<el-input type="textarea" id="cause" v-model="form['cause']" placeholder="请输入起因"
						v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','cause')) || (!form['development_history_id'] && $check_field('add','cause'))" :disabled="disabledObj['cause_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cause')">{{form['cause']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','development_significance') || $check_field('add','development_significance') || $check_field('set','development_significance')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发展意义" prop="development_significance">
					<el-input type="textarea" id="development_significance" v-model="form['development_significance']" placeholder="请输入发展意义"
						v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','development_significance')) || (!form['development_history_id'] && $check_field('add','development_significance'))" :disabled="disabledObj['development_significance_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','development_significance')">{{form['development_significance']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['development_history_id'] && $check_field('set','details')) || (!form['development_history_id'] && $check_field('add','details')) ">
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
				field: "development_history_id",
				url_add: "~/api/development_history/add?",
				url_set: "~/api/development_history/set?",
				url_get_obj: "~/api/development_history/get_obj?",
				url_upload: "~/api/development_history/upload?",

				query: {
					"development_history_id": 0,
				},

				form: {
					"title":'', // 标题
					"cover":'', // 封面
					"particular_year":'', // 年份
					"place":'', // 地点
					"dominating_figure":'', // 主要人物
					"cause":'', // 起因
					"development_significance":'', // 发展意义
					"details":'', // 详情
					"development_history_id": 0, // ID

				},
				disabledObj:{
					"title_isDisabled": false,
					"cover_isDisabled": false,
					"particular_year_isDisabled": false,
					"place_isDisabled": false,
					"dominating_figure_isDisabled": false,
					"cause_isDisabled": false,
					"development_significance_isDisabled": false,
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/development_history/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/development_history/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/development_history/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/development_history/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/development_history/view','get');
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
