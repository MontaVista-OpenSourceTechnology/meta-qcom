FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:qcom = " \
    file://0001-libweston-avoid-duplicate-texture2D_swizzle-overload.patch \
    file://0001-libweston-inline-color-pipeline-and-wireframe-helper.patch \
"
