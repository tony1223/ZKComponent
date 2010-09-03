zk.$package("mylabel");
mylabel.MyLabel = zk.$extends(zk.Widget, {
	$define:{
		value:[
			function(value){
				value = value || "";
				var node = this.$n();
				if(node) node.innerHTML = value;
				return value;
			}
		]
	},
	bind_ : function(evt) {
		this.$supers('bind_', arguments);
		var node = this.$n();
		if(node) node.innerHTML = this._value;
		this.domListen_(this, "onClick","_doMyLabelPress" );
	},

	_doMyLabelPress: function(){
		this.fire("onMyLabelPress", { date : new Date()});
	},

	getZclass: function () {
		var zcs = this._zclass;
		return zcs != null ? zcs: "z-mylabel" ;
	},
	unbind_ : function(evt) {
		this.$supers('unbind_', arguments);
	},
	appendChild : function(child){
		this.$supers("appendChild",arguments);
	}

});
