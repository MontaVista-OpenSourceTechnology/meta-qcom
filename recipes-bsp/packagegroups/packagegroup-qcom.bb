SUMMARY = "Extra userspace packages for QCOM platforms"

inherit packagegroup

PACKAGES = " \
    ${PN}-boot-essential \
    ${PN}-boot-additional \
    ${PN}-miscellaneous \
"

RDEPENDS:${PN}-boot-essential = " \
    pd-mapper \
    qrtr \
    rmtfs \
    tqftpserv \
"

RDEPENDS:${PN}-boot-additional = " \
    fastrpc \
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'opengl vulkan', 'msm-gbm-backend', '', d)} \
"
