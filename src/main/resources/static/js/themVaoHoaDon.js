function check() {
    const gioHangList = document.getElementById('gioHangList');
    const wrapBtn = document.getElementsByClassName('wrap-btn')[0];

    if (gioHangList.children.length > 0 && !document.getElementById('btnThanhToan')) {
        const button = document.createElement('button');
        button.setAttribute('id', 'btnThanhToan');
        button.setAttribute('class', 'btn btn-success mt-3 w-100');
        button.setAttribute('type', 'submit');
        button.setAttribute("form", "hoaDonForm");
        button.innerText = "Thanh toán";
        button.onclick = submitHoaDon;
        wrapBtn.appendChild(button);
    }
}

function themVaoHoaDon(button) {
    let idSanPham = button.getAttribute("data-id");
    let tenSanPham = button.getAttribute("data-ten-san-pham");
    let giaBan = parseFloat(button.getAttribute("data-gia-ban"));
    let soLuongTon = parseInt(button.getAttribute("data-so-luong"));
    let ms = button.getAttribute("data-ms");
    let nsx = button.getAttribute("data-nsx");
    let dsp = button.getAttribute("data-dsp");

    Swal.fire({
        title: 'Nhập số lượng cho ' + tenSanPham,
        input: 'number',
        inputAttributes: {
            min: 1,
            max: 100,
            step: 1
        },
        showCancelButton: true,
        confirmButtonText: 'Thêm vào hóa đơn',
        cancelButtonText: 'Hủy',
        preConfirm: (soLuong) => {
            if (!soLuong || soLuong <= 0) {
                Swal.showValidationMessage("Vui lòng nhập số lượng hợp lệ!");
                return false;
            }
            return soLuong;
        }
    }).then((result) => {
        if (result.isConfirmed) {
            let soLuong = result.value;

            let soLuongTonElement = document.getElementById("soLuong-" + idSanPham);
            let soLuongTonMoi = soLuongTon - soLuong;
            soLuongTonElement.textContent = soLuongTonMoi;
            button.setAttribute("data-so-luong", soLuongTonMoi);

            let gioHangList = document.getElementById("gioHangList");
            let li = document.createElement("li");
            li.classList.add("list-group-item");
            li.setAttribute("data-id", idSanPham);
            li.setAttribute("data-gia-ban", giaBan);
            li.setAttribute("data-so-luong", soLuong);
            li.innerHTML = `${tenSanPham} - ${ms} - ${nsx} - ${dsp}: ${soLuong} x ${giaBan} VNĐ`;
            gioHangList.appendChild(li);
            updateTotalPrice();
            check()
        }
    });
}

function updateTotalPrice() {
    let totalPrice = 0;
    let gioHangList = document.getElementById("gioHangList");

    gioHangList.querySelectorAll("li").forEach(li => {
        let soLuong = parseInt(li.getAttribute("data-so-luong"));
        let giaBan = parseFloat(li.getAttribute("data-gia-ban"));
        totalPrice += giaBan * soLuong;
    });

    const totalElement = document.getElementById("totalPrice");
    if (totalElement) {
        totalElement.innerText = `Thành tiền: ${totalPrice.toLocaleString()}đ`;
    }
}


function submitHoaDon() {
    let gioHangList = document.getElementById("gioHangList");
    let sanPhamChiTietInputs = document.getElementById("sanPhamChiTietInputs");
    let form = document.getElementById("hoaDonForm");

    sanPhamChiTietInputs.innerHTML = "";

    gioHangList.querySelectorAll("li").forEach(li => {
        let idSanPham = li.getAttribute("data-id");
        let soLuong = li.getAttribute("data-so-luong");
        let giaBan = li.getAttribute("data-gia-ban");

        let inputId = document.createElement("input");
        inputId.setAttribute("type", "hidden");
        inputId.setAttribute("name", "idCTSP");
        inputId.setAttribute("value", idSanPham);
        sanPhamChiTietInputs.appendChild(inputId);

        let inputSoLuong = document.createElement("input");
        inputSoLuong.setAttribute("type", "hidden");
        inputSoLuong.setAttribute("name", "soLuong");
        inputSoLuong.setAttribute("value", soLuong);
        sanPhamChiTietInputs.appendChild(inputSoLuong);

        let inputGiaBan = document.createElement("input");
        inputGiaBan.setAttribute("type", "hidden");
        inputGiaBan.setAttribute("name", "donGia");
        inputGiaBan.setAttribute("value", giaBan);
        sanPhamChiTietInputs.appendChild(inputGiaBan);
    });

    if (form) {
        form.addEventListener("submit", (event) => {
            showNotification('Thông báo!', 'Bạn có muốn tạo hóa đơn không?', 'info', null, event)
        })
    }
}
