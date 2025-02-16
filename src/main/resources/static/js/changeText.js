function changeText(select, span, dataTen) {
    const mySelect = document.getElementById(select);
    const mySpan = document.getElementById(span);
    const mySelectOption = mySelect.options[mySelect.selectedIndex];

    mySpan.textContent = mySelectOption.getAttribute(`data-ten-${dataTen}`) || '';
}