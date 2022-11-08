package com.yhgc.api.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public static R error() {
		return error(400, "操作失败");
	}

	public static R error(String msg) {
		return error(400, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(List<Object> list) {
		R r = new R();
		r.put("code",HttpStatus.SUCCESS);
		r.put("data", list);
		return r;
	}

	public static R  exist(){
		R r = new R();
		r.put("code",HttpStatus.EXIST);
		return r;
	}

	public static R ok(Object object) {
		R r = new R();
		r.put("code",HttpStatus.SUCCESS);
		r.put("data", object);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("code",HttpStatus.SUCCESS);
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.put("code",HttpStatus.SUCCESS);
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public static R ok(int code,String msg){
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
