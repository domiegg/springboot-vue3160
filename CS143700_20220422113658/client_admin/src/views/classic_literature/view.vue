<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','document_name') || $check_field('add','document_name') || $check_field('set','document_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="文献名称" prop="document_name">
					<el-input id="document_name" v-model="form['document_name']" placeholder="请输入文献名称"
							  v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','document_name')) || (!form['classic_literature_id'] && $check_field('add','document_name'))" :disabled="disabledObj['document_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','document_name')">{{form['document_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','author') || $check_field('add','author') || $check_field('set','author')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="作者" prop="author">
					<el-input id="author" v-model="form['author']" placeholder="请输入作者"
							  v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','author')) || (!form['classic_literature_id'] && $check_field('add','author'))" :disabled="disabledObj['author_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','author')">{{form['author']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publication_date') || $check_field('add','publication_date') || $check_field('set','publication_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发表日期" prop="publication_date">
					<el-date-picker :disabled="disabledObj['publication_date_isDisabled']" v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','publication_date')) || (!form['classic_literature_id'] && $check_field('add','publication_date'))" id="publication_date"
						v-model="form['publication_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','publication_date')">{{form['publication_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','place_of_publication') || $check_field('add','place_of_publication') || $check_field('set','place_of_publication')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发表地方" prop="place_of_publication">
					<el-input id="place_of_publication" v-model="form['place_of_publication']" placeholder="请输入发表地方"
							  v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','place_of_publication')) || (!form['classic_literature_id'] && $check_field('add','place_of_publication'))" :disabled="disabledObj['place_of_publication_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','place_of_publication')">{{form['place_of_publication']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','literature') || $check_field('add','literature') || $check_field('set','literature')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="文献" prop="literature">
					<div v-if="disabledObj['literature_isDisabled']">
						<div v-if="$check_field('get','literature')">
							<el-button type="primary" @click="$download($fullUrl(form['literature']),form['literature'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','literature')) || (!form['classic_literature_id'] && $check_field('add','literature'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_literature" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','literature')">
							<el-button type="primary" @click="$download($fullUrl(form['literature']),form['literature'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['classic_literature_id'] && $check_field('set','details')) || (!form['classic_literature_id'] && $check_field('add','details')) ">
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
				field: "classic_literature_id",
				url_add: "~/api/classic_literature/add?",
				url_set: "~/api/classic_literature/set?",
				url_get_obj: "~/api/classic_literature/get_obj?",
				url_upload: "~/api/classic_literature/upload?",

				query: {
					"classic_literature_id": 0,
				},

				form: {
					"document_name":'', // 文献名称
					"author":'', // 作者
					"publication_date":'', // 发表日期
					"place_of_publication":'', // 发表地方
					"literature":'', // 文献
					"details":'', // 详情
					"classic_literature_id": 0, // ID

				},
				disabledObj:{
					"document_name_isDisabled": false,
					"author_isDisabled": false,
					"publication_date_isDisabled": false,
					"place_of_publication_isDisabled": false,
					"literature_isDisabled": false,
					"details_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传文献
			 * @param {Object} param文件参数
			 */
			upload_literature(param){
				this.uploadFile(param.file, "literature");
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
        if (this.form["publication_date"].indexOf("-")===-1){
          this.form["publication_date"] = this.$toTime(parseInt(this.form["publication_date"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["publication_date"]) > 9999){
					this.form["publication_date"] = this.$toTime(parseInt(this.form["publication_date"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/classic_literature/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/classic_literature/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/classic_literature/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/classic_literature/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/classic_literature/view','get');
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
