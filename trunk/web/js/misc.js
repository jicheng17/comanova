(function($) {
	$(document).ready(function() {
		
		// Home Page Cycle
		$('#feature_images').cycle({
			speed: 4000
		});
		
		// Search Form
		$('#SearchForm_SearchForm_Search').click(function() {
			if($(this).attr('value') == 'Search') {
				$(this).attr('value', '');
			}
		})
	});
})(jQuery);