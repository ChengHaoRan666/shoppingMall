document.addEventListener('DOMContentLoaded', function () {
    // 获取所有具有 'add-to-wishlist' 类的元素
    var wishlistButtons = document.getElementsByClassName('add-to-wishlist');

    Array.from(wishlistButtons).forEach(function (button) {
        // 获取产品ID
        var productId = button.getAttribute('data-id');
        // 设置一个属性来标记是否已被收藏
        button.isStarred = false;

        // 检查产品是否已被收藏
        checkIfProductIsStarred(productId, button);

        button.addEventListener('click', function () {
            // 如果已被收藏，则不执行任何操作
            if (this.isStarred) {
                return;
            }

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/addStar?productId=' + productId, true);

            xhr.onload = function () {
                if (xhr.status === 401) {
                    // 用户未登录，重定向到登录页面
                    window.location.href = '/login';
                } else if (xhr.status === 200) {
                    // 收藏成功，更新UI并设置标志
                    updateUI(button, true);
                    button.isStarred = true;
                } else {
                    // 其他错误处理
                    alert('发生错误：' + xhr.statusText);
                }
            };

            xhr.send();
        });
    });
});

// 检查产品是否已被收藏
function checkIfProductIsStarred(productId, button) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'isStarred?productId=' + productId, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            // 如果已收藏，更新UI并设置标志
            var isStarred = JSON.parse(xhr.responseText).isStarred;
            if (isStarred) {
                updateUI(button, true);
                button.isStarred = true;
            }
        } else {
            // 处理错误情况
            alert('发生错误：' + xhr.statusText);
        }
    };

    xhr.send();
}

// 更新UI函数
function updateUI(button, isStarred) {
    var icon = button.querySelector('.fa');
    var tooltipp = button.querySelector('.tooltipp');

    if (isStarred) {
        if (icon) {
            icon.classList.remove('fa-heart-o');
            icon.classList.add('fa-heart');
            icon.style.color = 'red';
        }
        if (tooltipp) {
            tooltipp.textContent = '已收藏';
        }
    } else {
        if (icon) {
            icon.classList.remove('fa-heart');
            icon.classList.add('fa-heart-o');
            icon.style.color = 'initial';
        }
        if (tooltipp) {
            tooltipp.textContent = '加入收藏';
        }
    }
}
