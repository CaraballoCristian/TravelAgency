export function getRow(el, tag)  {
    if (el.hasAttribute(tag)) return el;
    return getRow(el.parentNode, tag);
}

