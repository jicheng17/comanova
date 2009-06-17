var currentFontSize = 4;

function revertStyles(fontsize) {
	currentFontSize = fontsize;
	changeFontSize(0);
}

function changeFontSize(sizeDifference) {
	// get css font size
	var rule = getRuleByName("body.font" + (currentFontSize + sizeDifference));
	if (rule) {
		document.body.style.fontSize = rule.style.fontSize;
		currentFontSize = currentFontSize + sizeDifference;
		createCookie("FontSize", currentFontSize, 365);
		equalHeight();
	}
	return;

};

function getRuleByName(ruleName) {
	for (i = 0; i < document.styleSheets.length; i++) {
		var style = document.styleSheets[i];
		var rules = style.cssRules ? style.cssRules : style.rules;
		if (rules) {
			for (j = 0; j < rules.length; j++) {
				if (rules[j].selectorText.trim().toUpperCase() == ruleName
						.trim().toUpperCase()) {
					return rules[j];
				}
			}
		}
	}
	return null;
}

function setActiveStyleSheet(title) {

	createCookie("ColorCSS", title, 365);
	window.location.reload();
	return;

	var i, a, main, arr;
	arr = document.getElementsByTagName("link");
	for (i = 0; (a = arr[i]); i++) {
		var ltitle = a.getAttribute("title");
		if (a.getAttribute("rel").indexOf("style") != -1 && ltitle) {
			a.disabled = true;
			if (ltitle == title)
				a.disabled = false;
		}
	}
	createCookie("ColorCSS", title, 365);
}

function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = "; expires=" + date.toGMTString();
	} else
		expires = "";
	document.cookie = name + "=" + value + expires + "; path=/";
}

function setScreenType(screentype) {
	bclass = document.body.className.trim();
	if (bclass.indexOf(' ') > 0) {
		bclass = bclass.replace(/^\w+/, screentype);
	} else {
		bclass = screentype + ' ' + bclass;
	}

	document.body.className = bclass;
	equalHeight();
	createCookie("ScreenType", screentype, 365);
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};

function changeToolHilite(oldtool, newtool) {
	if (oldtool != newtool) {
		if (oldtool) {
			oldtool.src = oldtool.src.replace(/-hilite/, '');
		}
		newtool.src = newtool.src.replace(/.gif$/, '-hilite.gif');
	}
}
// <![CDATA[
var goto_top_type = -1;
var goto_top_itv = 0;

function goto_top_timer() {
	var y = goto_top_type == 1 ? document.documentElement.scrollTop
			: document.body.scrollTop;
	var moveby = 35;

	y -= Math.ceil(y * moveby / 100);
	if (y < 0) {
		y = 0;
	}

	if (goto_top_type == 1) {
		document.documentElement.scrollTop = y;
	} else {
		document.body.scrollTop = y;
	}

	if (y == 0) {
		clearInterval(goto_top_itv);
		goto_top_itv = 0;
	}
}

function goto_top() {
	if (goto_top_itv == 0) {
		if (document.documentElement && document.documentElement.scrollTop) {
			goto_top_type = 1;
		} else if (document.body && document.body.scrollTop) {
			goto_top_type = 2;
		} else {
			goto_top_type = 0;
		}

		if (goto_top_type > 0) {
			goto_top_itv = setInterval('goto_top_timer()', 50);
		}
	}
}

// ]]>
// addEvent - attach a function to an event
function olAddEvent(obj, evType, fn) {
	if (obj.addEventListener) {
		obj.addEventListener(evType, fn, false);
		return true;
	} else if (obj.attachEvent) {
		var r = obj.attachEvent("on" + evType, fn);
		return r;
	} else {
		return false;
	}
}

function equalHeight() {
	// var box;
	if ($('ol-go1')) {
		var box = $$(getElementsByClass('ol-do', $('ol-go1'), "div"));
		if (box && box.length > 1) {
			makeEqualHeight(box);
		}
	}
}

function makeTransBg(el, bgimgdf, sizingMethod, type, offset) {
	var objs = el;
	if (!objs)
		return;
	if ($type(objs) != 'array')
		objs = [ objs ];
	if (!sizingMethod)
		sizingMethod = 'crop';
	if (!offset)
		offset = 0;
	var blankimg = siteurl + 'images/blank.png';
	objs.each( function(obj) {
		var bgimg = bgimgdf;
		if (obj.tagName == 'IMG') {
			// This is an image
			if (!bgimg)
				bgimg = obj.src;
			if (!(/\.png$/i).test(bgimg) || (/blank\.png$/i).test(bgimg))
				return;

			obj.setStyle('height', obj.offsetHeight);
			obj.setStyle('width', obj.offsetWidth);
			obj.src = blankimg;
			obj.setStyle('visibility', 'visible');
			obj.setStyle('filter',
					"progid:DXImageTransform.Microsoft.AlphaImageLoader(src="
							+ bgimg + ", sizingMethod='" + sizingMethod + "')");
		} else {
			// Background
			if (!bgimg)
				bgimg = obj.getStyle('backgroundImage');
			var pattern = new RegExp('url\s*[\(\"\']*([^\'\"\)]*)[\'\"\)]*');
			if ((m = pattern.exec(bgimg)))
				bgimg = m[1];
			if (!(/\.png$/i).test(bgimg) || (/blank\.png$/i).test(bgimg))
				return;
			if (!type) {
				obj.setStyles( {
					'background' :'none'
				});

				if (obj.getStyle('position') != 'absolute'
						&& obj.getStyle('position') != 'relative') {
					obj.setStyle('position', 'relative');
				}

				obj.getChildren().each(
						function(el) {
							if (el.getStyle('position') != 'absolute'
									&& el.getStyle('position') != 'relative') {
								el.setStyle('position', 'relative');
							}
							el.setStyle('z-index', 2);
						});
				// Create background layer:
				var bgdiv = new Element('IMG');
				bgdiv.src = blankimg;
				bgdiv.width = obj.offsetWidth - offset;
				bgdiv.height = obj.offsetHeight - offset;
				bgdiv.setStyles( {
					'position' :'absolute',
					'top' :0,
					'left' :-obj.getStyle('padding-left').toInt()
				});

				bgdiv.className = 'TransBG';

				bgdiv.setStyle('filter',
						"progid:DXImageTransform.Microsoft.AlphaImageLoader(src="
								+ bgimg + ", sizingMethod='" + sizingMethod
								+ "')");
				bgdiv.inject(obj, 'top');
				// alert(obj.innerHTML + '\n' + bgdiv.innerHTML);
			} else {
				obj.setStyle('filter',
						"progid:DXImageTransform.Microsoft.AlphaImageLoader(src="
								+ bgimg + ", sizingMethod='" + sizingMethod
								+ "')");
			}
		}
	}.bind(this));
}

function isIE6() {
	version = 0
	if (navigator.appVersion.indexOf("MSIE") != -1) {
		temp = navigator.appVersion.split("MSIE")
		version = parseFloat(temp[1])
	}
	return (version && (version < 7));
}

window.addEvent('load', function() {
	equalHeight();
});

var boxes = [];
showBox = function(box, focusobj, caller) {
	box = $(box);
	if (!box)
		return;
	if ($(caller))
		box._caller = $(caller);
	boxes.include(box);
	if (box.getStyle('display') == 'none') {
		box.setStyles( {
			display :'block',
			opacity :0
		});
	}
	if (box.status == 'show') {
		// hide
		box.status = 'hide';
		var fx = new Fx.Style(box, 'opacity');
		fx.stop();
		fx.start(box.getStyle('opacity'), 0);
		if (box._caller)
			box._caller.removeClass('show');
	} else {
		boxes.each( function(box1) {
			if (box1 != box && box1.status == 'show') {
				box1.status = 'hide';
				var fx = new Fx.Style(box1, 'opacity');
				fx.stop();
				fx.start(box1.getStyle('opacity'), 0);
				if (box1._caller)
					box1._caller.removeClass('show');
			}
		}, this);
		box.status = 'show';
		var fx = new Fx.Style(box, 'opacity', {
			onComplete : function() {
				if ($(focusobj))
					$(focusobj).focus();
			}
		});
		fx.stop();
		fx.start(box.getStyle('opacity'), 1);

		if (box._caller)
			box._caller.addClass('show');
	}
}
