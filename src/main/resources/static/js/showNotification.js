function showNotification(title = "Thông báo!", text = "Nội dung thông báo!", icon = "info", url = null, event = null) {
    if (event) {
        event.preventDefault();
    }

    if (!title) {
        title = 'Thông báo!'
    }

    if (!text) {
        text = "Nội dung thông báo!"
    }

    if (!icon) {
        icon = "info"
    }

    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        animation: false,
    }).then((result) => {
        if (result.isConfirmed && url) {
            window.location.href = url;
        } else if (result.isConfirmed && event) {
            event.target.closest("form").submit();
        }
    });

    return false;
}
