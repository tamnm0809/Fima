
document.addEventListener("DOMContentLoaded", function() {
	var selectedItem = window.location.pathname;
	if (selectedItem) {
		var selectedLink = document.querySelector('.item-link1[href="' + selectedItem + '"]');
		if (!selectedLink) {
			selectedLink = document.querySelector('.item-link1[href="' + selectedItem + '"]');
		}
		if (selectedLink) {
			selectedLink.classList.add('active');

			// Xác định parent item-list1 của mục được nhấp vào và mở nó
			var parentItem = selectedLink.closest('.item-list1');
			if (parentItem) {
				parentItem.style.backgroundColor = 'rgb(48, 83, 73)'; // Thay thế #your-color bằng mã màu tùy chỉnh		  
			}

			var parentCollapse = selectedLink.closest('.accordion-collapse');
			if (parentCollapse) {
				parentCollapse.classList.add('show');
			}
		}
	}

	var itemLinks = document.querySelectorAll('.item-link1');
	itemLinks.forEach(function(item) {
		item.addEventListener('click', function(event) {

			itemLinks.forEach(function(item) {
				item.classList.remove('active');
				item.parentNode.style.backgroundColor = '';
			});

			this.classList.add('active');
			this.parentNode.style.backgroundColor = '#305349';

			var parentCollapse = this.closest('.accordion-collapse');
			if (parentCollapse) {
				parentCollapse.classList.add('show');
			}

			var selectedItemHref = this.getAttribute('href');
			window.history.replaceState(null, '', selectedItemHref);
		});
	});
});