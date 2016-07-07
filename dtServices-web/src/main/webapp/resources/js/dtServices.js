function strPad(i,l,s) {
	var o = i.toString();
	if (!s) { 
		s = '0'; 
	}
	while (o.length < l) {
		o = s + o;
	}
	return o;
};

function geralNextFocus() {
	$('.geralNextFocus[type=text]').keydown(function(e) {
		if (e.which === 13) {
			var fields = $('.geralNextFocus[type=text]');
			var index = fields.index(this) + 1;
			fields.eq(index).focus();
			fields.eq(index).select();
			return false;
		}
	});
}

onBlurMilhar = function( field ){
	$( field ).val( strPad( $( field ).val(), 4 ) );
}

function isNumber(event) {
	if (event) {
		var charCode = (event.which) ? event.which : event.keyCode;
		if ((charCode < 48 || charCode > 57) 
				&& (charCode < 96 || charCode > 105)
				&& charCode != 8 && charCode != 46 && charCode != 9 && charCode != 13){
			console.log(charCode);
			return false;
		}
	}
	return true;
}

